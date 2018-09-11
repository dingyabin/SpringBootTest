package com.example.shiro.junit;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.shiro.realms.CustomerRealm;
import org.apache.catalina.realm.JDBCRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by MrDing
 * Date: 2018/9/6.
 * Time:22:46
 */
@RunWith(JUnit4.class)
public class ShiroTest {

    @Test
    public void simple() {

        SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
        simpleAccountRealm.addAccount("dingyabin", "521", "user");

        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(simpleAccountRealm);

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        subject.login(new UsernamePasswordToken("dingyabin", "521"));

        boolean authenticated = subject.isAuthenticated();
        boolean user = subject.hasRole("use2r");

        System.out.println("认证结果:" + authenticated);
        System.out.println("授权:" + user);
    }


    @Test
    public void simpleIni() {

        IniRealm iniRealm = new IniRealm("classpath:shiro/shiro.ini");

        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(iniRealm);

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        subject.login(new UsernamePasswordToken("dingyabin", "123"));

        subject.checkPermission("delete");
    }


    @Test
    public void simpleJdbc() {

        JdbcRealm jdbcRealm = new JdbcRealm();

        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/weight_manager?characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");

        jdbcRealm.setDataSource(dataSource);

        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(jdbcRealm);

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        subject.login(new UsernamePasswordToken("dingyabin", "1234"));

        subject.checkPermission("delete");
    }





    @Test
    public void simpleCustomer() {

        CustomerRealm customerRealm = new CustomerRealm();

        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        customerRealm.setCredentialsMatcher(matcher);

        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(customerRealm);

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        subject.login(new UsernamePasswordToken("dingyabin", "123"));

        subject.checkRole("admin");
        subject.checkPermission("delete");
    }


}
