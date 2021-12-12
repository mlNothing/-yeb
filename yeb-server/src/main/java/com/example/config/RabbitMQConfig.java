package com.example.config;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.pojo.MailConstants;
import com.example.pojo.MailLog;
import com.example.service.IMailLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**RabbitMq配置类
 * @author mlNothing
 * @date 2021/12/12 12:02
 */
@Configuration
public class RabbitMQConfig {

    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQConfig.class);

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;
    @Autowired
    private IMailLogService mailLogService;

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        /**
         * create by: mlNothing
         * description: 消息回调
         * create time: 2021/12/12 12:14
         * @param data 消息唯一标识
         * @param ack 确认结果
         * @param cause 失败原因
         * @return
         */
        rabbitTemplate.setConfirmCallback((data,ack,cause)->{
            String msgId = data.getId();
            if(ack){
                LOGGER.info("{}=====>消息发送成功",msgId);
                mailLogService.update(new UpdateWrapper<MailLog>().set("status",1).eq("msgId",msgId));
            }else {
                LOGGER.error("{}=====>消息发送失败",msgId);
            }
        });

        /*msg:消息失败原因
        *repCpde:消息响应码
          *repText:相应描述
         * @param exchange:交换机
         * @return routingKey:路由键
         */
        rabbitTemplate.setReturnsCallback((msg)->{
            LOGGER.error("{}===消息queue发送失败",msg.getMessage().getBody());
        });

        return rabbitTemplate;
    }


    @Bean
    public Queue queue(){
        return new Queue(MailConstants.MAIL_QUEUE_NAME, true);
    }
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(MailConstants.MAIL_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(){
        return  BindingBuilder.bind(queue()).to(directExchange()).with(MailConstants.MAIL_ROUTING_NAME);
    }

}
