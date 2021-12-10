package com.example.mail;


import com.example.YebServer;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * create by: mlNothing
 * description: 接受短信
 * create time: 2021/12/10 16:29
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MailApplication
{

    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class,args);
    }

    @Bean
    public Queue queue(){
        return new Queue("mail.welcome");
    }
}
