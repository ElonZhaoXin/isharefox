package com.isharefox.share.trade.entity;

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
public class UserAccount extends Model<UserAccount> {

    private static final long serialVersionUID = 1L;

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