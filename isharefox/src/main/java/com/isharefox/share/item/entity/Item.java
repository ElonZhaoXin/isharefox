package com.isharefox.share.item.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品资源表
 * </p>
 *
 * @author isharefox
 * @since 2020-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 资源所有者id
     */
    private Integer userId;

    /**
     * 密码
     */
    private String zipPassword;

    /**
     * 商品描述
     */
    private String detail;

    /**
     * 资源出售价格/元
     */
    private BigDecimal amount;

    /**
     * 0-创建，未发布；1=上架，正常售卖；2-下架
     */
    private String status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
