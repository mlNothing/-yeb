package com.example.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author mlNothing
 * @date 2021/11/12 15:26
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    private ApiInfo apiInfo(){
        return  new ApiInfoBuilder()
                .title("云e办接口文档")
                .description("云e办接口文档")
                .contact(new Contact("ml","http://localhost:8081/doc.html","utmostfires@gmail.com"))
                .version("1.0")
                .build();
    }

    @Bean
    public Docket createRestApi(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//               为当前的controller创建swagger自动创建文档
                .apis(RequestHandlerSelectors.basePackage("com.yeb.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
