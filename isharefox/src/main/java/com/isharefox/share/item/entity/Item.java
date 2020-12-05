package com.isharefox.share.item.entity;

import java.math.BigDecimal;
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
public class Item extends Model<Item> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
     * 资源状态，0-创建，未发布；1=上架，正常售卖；2-下架
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
