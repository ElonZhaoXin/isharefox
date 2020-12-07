package com.isharefox.share.auth;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.isharefox.share.user.user.entity.User;
import com.isharefox.share.user.user.service.IUserService;
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
 * 扩展shiro，自定义认证授权管理
 */
@Slf4j
@Service
public class CustomRealm extends AuthorizingRealm {
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
        KaptchaUsernamePasswordToken token = (KaptchaUsernamePasswordToken) authenticationToken;
        if (!(ObjectUtils.nullSafeEquals(token.getKaptcha(),ShiroUtil.getSessionKaptcha()))) {
            throw new AuthenticationException("验证码有误");
        }
        User user = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getEmail, token.getUsername()));
        if (user == null) {
            log.warn("用户信息:[{}]不存在", new Gson().toJson(token));
            throw new UnknownAccountException("用户信息或密码错误");
        }
        if (ObjectUtils.nullSafeEquals(user.getPwd(), new String(token.getPassword()))) {
            log.info("==================认证成功,结束===================");
            ShiroUtil.saveCurrentUser(user);
            return new SimpleAuthenticationInfo(user, token.getPassword(), getName());
        } else {
            log.error("密码不符");
            throw new UnknownAccountException("用户或或密码错误");
        }
    }
}
