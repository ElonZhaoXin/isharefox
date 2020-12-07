package com.isharefox.share.auth;
import com.isharefox.share.user.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * shiro工具类
 */
public class ShiroUtil {

    private static final String KAPTCHA_KEY = "kaptcha";

    /**
     * @return 获取当前登录用户
     */
    public static User currentUser() {
        return (User)SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * @return 获取session
     */
    public static Session currentSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 保存当前用户对象到session
     * @param user 当前用户
     */
    public static void saveCurrentUser(Object user) {
        SecurityUtils.getSubject().getSession().setAttribute("user", user);
    }

    /**
     * 保存验证码到session
     * @param kaptcha 验证码
     */
    public static void putKaptcha(String kaptcha) {
        SecurityUtils.getSubject().getSession().setAttribute("kaptcha", kaptcha);
    }

    /**
     * @return 当前session的验证码
     */
    public static String getSessionKaptcha() {
        String kaptcha = (String)SecurityUtils.getSubject().getSession().getAttribute("kaptcha");
        return kaptcha;
    }

}
