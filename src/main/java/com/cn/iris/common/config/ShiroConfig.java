package com.cn.iris.common.config;

import com.cn.iris.common.core.shiro.LoginRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author: IrisNew
 * Description: shiro 配置
 * Date: 2018/6/6 16:45
 */
@Configuration
public class ShiroConfig {

    /**
     * 缓存管理器 使用Ehcache实现
     */
    @Bean
    public CacheManager getCacheShiroManager(EhCacheManagerFactoryBean ehcache) {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(ehcache.getObject());
        return ehCacheManager;
    }

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager){
        ShiroFilterFactoryBean sfbBean = new ShiroFilterFactoryBean();
        sfbBean.setSecurityManager(securityManager);
        //设置默认登陆页
        sfbBean.setLoginUrl("/welcome");
        //登陆成功后跳转的url
        sfbBean.setSuccessUrl("/index");
        //设置权限不足跳转
        sfbBean.setUnauthorizedUrl("/403");
        //自定义过滤器
        /*Map<String, Filter> filters=new HashMap<>();
        filters.put("per",getPermissionFilter());
        filters.put("verCode",getVerfityCodeFilter());
        sfbBean.setFilters(filters);*/

        /** 配置访问权限
         * 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
         * anon  不需要认证
         * authc 需要认证
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/css/**","anon");
        filterMap.put("/js/**","anon");
        filterMap.put("/welcome","anon");
        filterMap.put("/userlogin","anon");
        //filterMap.put("/login","anon");
        filterMap.put("/getVeCode","anon");
        filterMap.put("/logout","logout");
        filterMap.put("/plugin/**","anon");
        //filterMap.put("/user/**","per");
        filterMap.put("/**","authc");
        sfbBean.setFilterChainDefinitionMap(filterMap);
        return sfbBean;
    }

    // 配置核心安全事务管理器
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("loginRealm") LoginRealm loginRealm,CacheManager cacheShiroManager) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //自定义的权限登录器
        manager.setRealm(loginRealm);
        //自定义的session管理器
//        manager.setSessionManager(sessionManager);
        //自定义缓存策略
        manager.setCacheManager(cacheShiroManager);
        return manager;
    }

    //配置自定义的权限登录器
    @Bean(name = "loginRealm")
    public LoginRealm getLoginRealm(){
        LoginRealm realm= new LoginRealm();
        realm.setCredentialsMatcher(credentialsMatcher());
        return realm;
    }

    // 配置自定义的密码匹配比较器
    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new SimpleCredentialsMatcher();
    }

    /**
     * 启用shrio授权注解拦截方式，AOP式方法级权限检查
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor as=new AuthorizationAttributeSourceAdvisor();
        as.setSecurityManager(securityManager);
        return as;
    }
}
