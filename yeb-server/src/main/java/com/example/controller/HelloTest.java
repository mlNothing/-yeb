package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mlNothing
 * @date 2021/11/15 21:11
 */
@RestController
public class HelloTest {
    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/employee/basic/hello")
    public String hello2(){
        return "/employee/basic/**";
    }
    @GetMapping("/employee/advanced/**")
    public String hello3(){
        return "/employee/advanced/**";
    }
}