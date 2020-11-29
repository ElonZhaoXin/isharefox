package com.isharefox.share.web.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemAddDto {

    /**
     * 资源所有者id
     */
    private Integer userId;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(max = 9,message = "密码长度不能大于9位")
    private String zipPassword;

    /**
     * 商品描述
     */
    @NotBlank
    @Length(max = 20, message = "商品描述太长")
    private String detail;

    /**
     * 资源出售价格/元
     */
    @DecimalMax(value = "1000")
    @DecimalMin(value = "0")
    @NotNull
    private BigDecimal amount;

    /**
     * 0-创建，未发布；1=上架，正常售卖；2-下架
     */
    private String status;
}
