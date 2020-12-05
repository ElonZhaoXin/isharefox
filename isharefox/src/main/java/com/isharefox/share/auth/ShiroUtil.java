package com.isharefox.share.auth;
import com.isharefox.share.user.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * shiro工具类
 */
public class ShiroUtil {

    /**
     * 获取当前登录用户
     * @return
     */
    public static User currentUser() {
        return (User)SecurityUtils.getSubject().getPrincipal();
    }

    public static Session currentSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static void saveCurrentUser(Object user) {
        SecurityUtils.getSubject().getSession().setAttribute("user", user);
    }
}
