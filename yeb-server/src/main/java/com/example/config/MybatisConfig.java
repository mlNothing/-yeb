package com.example.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Configuration;

/**
 * @author mlNothing
 * @date 2021/12/8 19:23
 */
@Configuration
public class MybatisConfig {

    /**
     * create by: mlNothing
     * description: 分页拦截器，分页插件
     * create time: 2021/12/8 19:25
     * @param 
     * @return 
     */
    public PaginationInnerInterceptor paginationInnerInterceptor(){
        return  new PaginationInnerInterceptor();
    }
}
