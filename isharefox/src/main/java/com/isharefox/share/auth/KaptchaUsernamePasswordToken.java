package com.isharefox.share.auth;
import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 验证码token
 */
@Data
public class KaptchaUsernamePasswordToken extends UsernamePasswordToken {
    private String kaptcha;

    public KaptchaUsernamePasswordToken(String username,
                                        String password,
                                        String kaptcha) {
        super(username, password);
        this.kaptcha = kaptcha;
    }

    public KaptchaUsernamePasswordToken(String username,
                                        String password,
                                        boolean rememberMe,
                                        String host,
                                        String kaptcha) {
        super(username, password, rememberMe, host);
        this.kaptcha = kaptcha;
    }

}
