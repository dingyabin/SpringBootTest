package com.example.shiro.realms;

import com.example.shiro.bean.User;
import com.example.shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * Created by MrDing
 * Date: 2018/9/10.
 * Time:23:30
 */
public class CustomerRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();

        User user = userService.selectByUserNameWithRolesAndPermissions(username);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(user.getRoles());
        simpleAuthorizationInfo.setStringPermissions(user.getPermissions());
        return simpleAuthorizationInfo;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userService.selectByUserNameWithRolesAndPermissions(username);
        if (user == null) {
            return null;
        }
        ByteSource salt = ByteSource.Util.bytes(user.getPasswordSalt());
        return new SimpleAuthenticationInfo(username, user.getPassword(), salt, getName());
    }


}
