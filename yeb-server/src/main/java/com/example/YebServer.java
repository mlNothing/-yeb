package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.example.mapper")
public class YebServer
{
    public static void main(String[] args) {
        SpringApplication.run(YebServer.class,args);
    }

}
