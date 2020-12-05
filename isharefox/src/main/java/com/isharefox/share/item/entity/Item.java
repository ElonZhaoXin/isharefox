package com.isharefox.share.item.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
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
public class Item extends BaseEntity<Item> {

    private static final long serialVersionUID = 1L;

    /**
     * 资源编号，在支付URL中：http://isharefox.com/share/userId/resourceId
     */
    private String resourceId;

    /**
     * 解压密码
     */
    private String zipPwd;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 收费金额
     */
    private BigDecimal amount;

    /**
     * 客户编号
     */
    private String userId;

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
