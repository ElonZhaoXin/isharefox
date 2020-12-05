package com.isharefox.share.item.entity;

import java.math.BigDecimal;
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
     * 资源存放地址，例如百度网盘url
     */
    private String url;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
