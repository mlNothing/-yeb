package com.example.exception;

import com.example.pojo.RespBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @ControllerAdvice方式只能处理控制器抛出的气场。此时请求已经进入控制中，可以定义多个
 * 拦截方法，拦截不同的异常类，并且可以获取抛出的异常信息，自由度更大。
 * errorController类方式可以处理所有的异常，包括未进入控制器的错误，比如404，401等错误
 * @author mlNothing
 * @date 2021/12/6 22:31
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public RespBean mysqlException(SQLException e){
        if (e instanceof SQLIntegrityConstraintViolationException){
            return RespBean.error("该数据有关联数据，操作失败!");
        }return RespBean.error("数据库异常，操作失败");
    }
}
