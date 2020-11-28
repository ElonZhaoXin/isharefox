package com.isharefox.share.web.auth;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.isharefox.share.user.regist.entity.User;
import com.isharefox.share.user.regist.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * 认证授权管理
 */
@Slf4j
@Service
public class CustomRealm extends AuthorizingRealm implements InitializingBean {
    @Autowired
    IUserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("==================CustomRealm doGetAuthorizationInfo===================");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("==================认证开始===================");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getEmail, token.getUsername()));
        if (user == null) {
            log.warn("用户信息:[{}]不存在", new Gson().toJson(token));
            throw new UnknownAccountException("登录失败");
        }
        if (ObjectUtils.nullSafeEquals(user.getPassword(), new String(token.getPassword()))) {
            log.info("==================认证成功,结束===================");
            return new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());
        } else {
            log.error("用户登录失败");
            throw new UnknownAccountException("登录失败");
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        //super.setCredentialsMatcher(new PasswordMatcher());
    }
}
