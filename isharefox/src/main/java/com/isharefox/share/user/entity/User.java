package com.isharefox.share.user.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaoxin
 * @since 2020-11-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Model<User> {

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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
