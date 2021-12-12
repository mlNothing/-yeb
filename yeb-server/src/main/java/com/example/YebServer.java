package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * create by: mlNothing
 * description:
 * @author mlNothing
 * create time: 2021/12/7 10:13
 * @param
 * @return
 */
@SpringBootApplication
@MapperScan("com.example.mapper")
@EnableScheduling
public class YebServer
{
    public static void main(String[] args) {
        SpringApplication.run(YebServer.class,args);
    }

}
