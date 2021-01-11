package com.isharefox.share.item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemAddDto {
	 /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Length(max = 60,message = "用户名长度不能大于60位")
    private String username;
    
    /**
     * 登录密码
     */
    @NotBlank(message = "登录密码不能为空")
    @Length(max = 60,message = "登录密码长度不能大于60位")
    private String password;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(max = 6,message = "密码长度不能大于6位")
    private String zipPwd;

    /**
     * 商品描述
     */
    @NotBlank
    @Length(max = 20, message = "商品描述太长")
    private String description;

    /**
     * 资源出售价格/元
     */
    @DecimalMax(value = "1000")
    @DecimalMin(value = "0")
    @NotNull
    private BigDecimal amount;

    /**
     * 资源存放地址，例如百度网盘url
     */
    @NotBlank
    @URL
    private String url;

    /**
     * 0-创建，未发布；1=上架，正常售卖；2-下架
     */
    private String status;


}
