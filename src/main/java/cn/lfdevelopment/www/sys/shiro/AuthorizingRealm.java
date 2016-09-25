/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www.sys.shiro;

import cn.lfdevelopment.www.app.common.login.pojo.SysUser;
import cn.lfdevelopment.www.app.common.login.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by LiuFa on 2016/9/12.
 * cn.lfdevelopment.www.sys.shiro
 * DevelopmentApp
 */
@Component("userRealm")
public class AuthorizingRealm extends org.apache.shiro.realm.AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    public void setCachingEnabled(boolean cachingEnabled) {
        super.setCachingEnabled(false);
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("管理员");

        //这里没有设置是因为在filters里面已经设置了rolesFilter和rolesOrFilte
        //authorizationInfo.setRoles(Set);
        //系统没有涉及精确的权限判断
        //authorizationInfo.setStringPermissions(Set);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        SysUser user = userService.getUserByName(username);

        if(user == null) {
            //账号不存在
            throw new UnknownAccountException();
        }

        if(user.getInUse()==0) {
            //账号被锁定
            throw new LockedAccountException();
        }

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("user", user);
        return new SimpleAuthenticationInfo(
                user.getLoginName(),
                user.getPassword(),
                getName()
        );
    }

/*    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }*/

    private void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    private void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}