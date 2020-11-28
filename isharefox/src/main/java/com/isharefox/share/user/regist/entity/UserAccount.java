package com.isharefox.share.user.regist.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户收款账户表
 * </p>
 *
 * @author isharefox
 * @since 2020-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 0-微信；1-支付宝
     */
    private String type;

    /**
     * 支付宝：手机号/账号
     */
    private String accountNo;

    /**
     * 0-异常；1-正常
     */
    private String status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
