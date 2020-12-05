package com.isharefox.share.auth;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
    /**
     * 用户登录页面
     */
    private static final String userLoginUrl = "/user/login";

    @Override
    protected boolean pathsMatch(String pattern, String path) {
        return super.pathsMatch(userLoginUrl, path);
    }
}
