package com.example.shiro.realms;

import com.google.common.collect.Sets;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by MrDing
 * Date: 2018/9/10.
 * Time:23:30
 */
@Component("customerRealm")
public class CustomerRealm extends AuthorizingRealm {

    private static final Map<String, String> users = new HashMap<>();
    private static final Map<String, Set<String>> roles = new HashMap<>();
    private static final Map<String, Set<String>> permits = new HashMap<>();

    static {
        users.put("dingyabin", "123");

        roles.put("dingyabin", Sets.newHashSet("admin", "user"));

        permits.put("admin", Sets.newHashSet("select", "delete"));
        permits.put("user", Sets.newHashSet("update", "delete"));

    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        Set<String> role = roles.get(username);

        Set<String> pers = Sets.newHashSet();
        role.stream()
                .filter(permits::containsKey)
                .forEach(r -> pers.addAll(permits.get(r)));

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(role);
        simpleAuthorizationInfo.setStringPermissions(pers);
        return simpleAuthorizationInfo;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String pwd = users.get(username);
        if (pwd == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(username, pwd, ByteSource.Util.bytes("DYB"), getName());
    }


}
