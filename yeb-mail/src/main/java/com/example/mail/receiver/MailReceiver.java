package com.example.mail.receiver;

import com.example.pojo.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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
    /**
     * create by: mlNothing
     * description: 监听mail.welcome队列中的消息
     * create time: 2021/12/10 11:26
     * @param employee employee
     */
    @RabbitListener(queues = "mail.welcome")
    public void sendMail(Employee employee){
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        try {
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
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            LOGGER.error("邮件发送失败 ========>",e.getMessage());
        }
    }
}


