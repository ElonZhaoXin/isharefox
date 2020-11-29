package com.isharefox.share.web.auth;
import com.isharefox.share.user.regist.entity.User;
import org.apache.shiro.SecurityUtils;

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

    public static void saveCurrentUser(Object user) {
        SecurityUtils.getSubject().getSession().setAttribute("user", user);
    }
}
