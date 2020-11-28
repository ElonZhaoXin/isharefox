package com.isharefox.share.web.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDto {

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
    @DecimalMax(value = "1000", message = "资源出售价格过高")
    @NotBlank
    private BigDecimal amount;

    /**
     * 0-创建，未发布；1=上架，正常售卖；2-下架
     */
    @NotBlank
    @Pattern(regexp = "^[0-2]$")
    private String status;
}
