package com.isharefox.share.auth;
import com.google.gson.Gson;
import com.isharefox.share.common.api.BaseResponse;
import com.isharefox.share.common.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 扩展shiro 表单认证过滤器
 * 增加登录验证码识别
 */
@Slf4j
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
    /**
     * 用户登录页面
     */
    private static final String userLoginUrl = "/user/login";

    private static final String KAPTCHA_ATTR = "kaptcha";

    /**
     * 后台登录方式
     */
    private static final String BACK_LOGIN_ATTR = "backLogin";

    @Override
    protected AuthenticationToken createToken(String username, String password,
                                              ServletRequest request, ServletResponse response) {
        String kaptcha = WebUtils.getCleanParam(request, KAPTCHA_ATTR);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return new KaptchaUsernamePasswordToken(username, password, rememberMe, host, kaptcha);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        if (isBackLogin(request)) {
            //如果是后台登录成功，不重定向，继续执行接口操作
            return true;
        }
        return super.onLoginSuccess(token, subject, request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        if (log.isDebugEnabled()) {
            log.debug( "Authentication exception", e );
        }
        setFailureAttribute(request, e);
        //登录失败，不继续执行
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json");

            BaseResponse errorResp = BaseResponse.builder()
                    .code(ResultCode.FAILURE)
                    .message(e.getMessage())
                    .build();
            httpServletResponse.getWriter().write(new Gson().toJson(errorResp));
        } catch (IOException ex) {
            log.error("登录失败IO流异常", e);
        }
        return false;
    }

    /**
     * 是否后台登录
     * @param request
     * @return
     */
    private boolean isBackLogin(ServletRequest request) {
        String login = WebUtils.getCleanParam(request, BACK_LOGIN_ATTR);
        return "1".equals(login);
    }

    @Override
    protected boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        return super.isLoginRequest(request, response)
                || isBackLogin(request);
    }
}
