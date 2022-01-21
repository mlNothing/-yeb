package com.example.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mlNothing
 * @date 2021/12/8 19:23
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * create by: mlNothing
     * description: 分页拦截器，分页插件
     * create time: 2021/12/8 19:25
     * @param 
     * @return 
     */
    @Bean
    public MybatisPlusInterceptor paginationInnerInterceptor(){
        MybatisPlusInterceptor page = new MybatisPlusInterceptor();
        page.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return page;
    }
}
