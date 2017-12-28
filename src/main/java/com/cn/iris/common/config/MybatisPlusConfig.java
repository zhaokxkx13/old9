package com.cn.iris.common.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: IrisNew
 * Description:mybatis-plus配置
 * Date: 2017/12/12 17:11
 */
@Configuration
@MapperScan("com.cn.iris.*.mapper*")
public class MybatisPlusConfig {

    /**
     *	 mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
