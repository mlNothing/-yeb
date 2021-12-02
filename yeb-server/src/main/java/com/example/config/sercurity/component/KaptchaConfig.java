package com.example.config.sercurity.component;

import com.google.code.kaptcha.impl.DefaultKaptcha;

import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author mlNothing
 * @date 2021/12/2 10:13
 * 验证码的配置
 */
@Component
public class KaptchaConfig {
    /**
     * create by: mlNothing
     * description: 验证码的配置
     * create time: 2021/12/2 10:47
     * @param: 
     * @return 
     */
    @Bean
    public DefaultKaptcha getDefaultKaptcha(){
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border","yes");
        properties.setProperty("kaptcha.border.color","yellow");
        properties.setProperty("kaptcha.border.thickness","1");
        properties.setProperty("kaptcha.session.key","code");
        properties.setProperty("kaptcha.textproducer.font.color","blue");
        properties.setProperty("kaptcha.textproducer.font.names","Arial,宋体,楷体");
        properties.setProperty("kaptcha.textproducer.font.size","40");
        properties.setProperty("kaptcha.textproducer.char.string","");
        properties.setProperty("kaptcha.textproducer.char.length","4");
        properties.setProperty("kaptcha.textproducer.char.space","4");
        properties.setProperty("kaptcha.image.width","200");
        properties.setProperty("kaptcha.image.height","80");
        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return  kaptcha;
    }
}
