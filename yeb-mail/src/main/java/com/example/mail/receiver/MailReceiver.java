package com.example.mail.receiver;

import com.example.pojo.Employee;
import com.example.pojo.MailConstants;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;


/**
 * @author mlNothing
 * @date 2021/12/10 11:00
 */
@Component
public class MailReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailReceiver.class);
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailProperties mailProperties;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * create by: mlNothing
     * description: 监听mail.welcome队列中的消息
     * create time: 2021/12/10 11:26
     * @param message employee
     * @param channel channel
     */
    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void sendMail(Message message, Channel channel){
//        拿到员工类
        Employee employee = (Employee) message.getPayload();
        MessageHeaders headers = message.getHeaders();
//        序号
        long tag = (long) headers.get(AmqpHeaders.DELIVERY_TAG);
        String msgId = (String) headers.get("spring_returned_message_correlation");
        HashOperations hashOperations = redisTemplate.opsForHash();


        try {
            if (hashOperations.entries("mail_log").containsKey(msgId)){
                LOGGER.error("消息已经被消费{}",msgId);
//                手动确认消费 tag：消息虚高 false:是否确认多条
                channel.basicAck(tag,false);
            }
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(msg);
            //设置发送人
            messageHelper.setFrom(mailProperties.getUsername());
            //设置收件人
            messageHelper.setTo(employee.getEmail());
            //设置发送主体
            messageHelper.setSubject("入职欢迎邮件");
            //设置发送日期
            messageHelper.setSentDate(new Date());
            //设置邮件内容
            Context context = new Context();
            //如下参数对应mail.html中模板引擎的参数
            context.setVariable("name",employee.getName());
            context.setVariable("posName",employee.getPosition().getName());
            context.setVariable("joblevelName",employee.getJoblevel().getName());
            context.setVariable("departmentName",employee.getDepartment().getName());
            String mail = templateEngine.process("mail", context);
            //参数1：邮件参数 参数2：是否是html邮件
            messageHelper.setText(mail,true);
            //发送邮件
            javaMailSender.send(msg);
            LOGGER.info("邮件发送成功");
//            将消息id存入redis
            hashOperations.put("mail_log",msgId,"OK");
//            手动确认
            channel.basicAck(tag,false);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            /**
             * 手动确认消息，拒绝接收到的消息，退回到队列，也就是说如果消息的消费出现异常，会将消息退回到队列中
             * @tag 消息序号
             * @multiple 是否处理多条
             * @requeue 是否要退回到队列，如果是false，消息不会重发，
            会把消息打入死信队列。如果是true，会无限次重发导致死循环，不建议加try-catch
             */
            try {
                channel.basicNack(tag, false, true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            LOGGER.error("邮件发送失败 ========>",e.getMessage());
        }
    }
}


