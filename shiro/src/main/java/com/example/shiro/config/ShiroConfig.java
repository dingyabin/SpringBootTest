package com.example.shiro.config;

import com.example.shiro.filter.shirofilter.AnyRoleOkFilter;
import com.example.shiro.realms.CustomerRealm;
import com.google.common.collect.Maps;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by MrDing
 * Date: 2018/9/6.
 * Time:22:13
 */

@Configuration
public class ShiroConfig {


    @Resource
    private CustomerRealm customerRealm;


    @Bean("anyRoleOkFilter")
    public AuthorizationFilter anyRoleOkFilter(){
       return new AnyRoleOkFilter();
    }


    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager());
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403.jsp");

        Map<String, String> map = shiroFilterFactoryBean.getFilterChainDefinitionMap();
        map.put("/health", "authc");
        map.put("/init/**", "authc");
        map.put("/**", "anon");

        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("anyRoleOkFilter", anyRoleOkFilter());

        return shiroFilterFactoryBean;
    }




    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        customerRealm.setCredentialsMatcher(matcher);
        securityManager.setRealm(customerRealm);
        return securityManager;
    }




   //自动调用生命周期函数(init,destory)
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }



    //注解支持
    @Bean("authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(defaultWebSecurityManager());
        return new AuthorizationAttributeSourceAdvisor();
    }


}
