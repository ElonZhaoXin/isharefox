package com.isharefox.share.user.user.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.isharefox.share.kernel.BaseEntity;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户编号
     */
    private String userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 登录邮箱
     */
    private String email;

    /**
     * 登录密码
     */
    private String pwd;

    /**
     * 手机号
     */
    private String cellPhoneNum;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
