package com.cn.iris;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * @Author: IrisNew
 * @Description:springBoot启动类
 * @Date: 2017/12/6 17:18
 */
@SpringBootApplication
public class IrisApplication extends WebMvcConfigurerAdapter{

    private final static Logger logger = LoggerFactory.getLogger(IrisApplication.class);

    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(gunsProperties.getSwaggerOpen()){
            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars*//**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }*/

    public static void main(String[] args) {
        SpringApplication.run(IrisApplication.class, args);
        logger.info("IrisApplication is success!");
    }
}
