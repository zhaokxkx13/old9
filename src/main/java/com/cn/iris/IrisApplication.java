package com.cn.iris;

import com.cn.iris.common.interceptor.LoginInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * @Author: IrisNew
 * @Description:springBoot启动类
 * @Date: 2017/12/6 17:18
 */
@SpringBootApplication
public class IrisApplication extends WebMvcConfigurerAdapter{

    private final static Logger logger = LoggerFactory.getLogger(IrisApplication.class);

    @Autowired
    private LoginInterceptor loginInterceptor;

    public static void main(String[] args) {
        SpringApplication.run(IrisApplication.class, args);
        logger.info("IrisApplication is success!");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){

        super.addInterceptors(registry);
        //登陆拦截(除了登陆操作)
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/welcome")
                .excludePathPatterns("/userlogin");
    }

}
