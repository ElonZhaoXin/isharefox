package com.isharefox.share.user.account.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserAccount extends Model<UserAccount> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 客户编号
     */
    private String userId;

    /**
     * 账户类型，0-微信；1-支付宝
     */
    private String type;

    /**
     * 1-支付宝：手机号/账号；
     */
    private String accountNo;

    /**
     * 0-异常；1-正常
     */
    private String status;

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
        return this.id;
    }

}
