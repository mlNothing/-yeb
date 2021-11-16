package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mlNothing
 * @date 2021/11/15 21:11
 */
@RestController
public class helloC {
    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

}