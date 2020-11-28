package com.isharefox.share.user.regist.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author isharefox
 * @since 2020-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * email
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 0-异常；1-正常
     */
    private String status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
