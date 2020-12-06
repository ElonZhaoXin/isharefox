package com.isharefox.share.auth;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 扩展shiro 表单认证过滤器
 * 增加登录验证码识别
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
    /**
     * 用户登录页面
     */
    private static final String userLoginUrl = "/user/login";

    private static final String KAPTCHA_ATTR = "kaptcha";

    @Override
    protected AuthenticationToken createToken(String username, String password,
                                              ServletRequest request, ServletResponse response) {
        String kaptcha = WebUtils.getCleanParam(request, KAPTCHA_ATTR);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return new KaptchaUsernamePasswordToken(username, password, rememberMe, host, kaptcha);
    }

    @Override
    protected boolean pathsMatch(String pattern, String path) {
        return super.pathsMatch(userLoginUrl, path);
    }

}
