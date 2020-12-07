package com.isharefox.share.user.register.dto;

import com.isharefox.share.common.validation.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistDto {
    /**
     * 昵称
     */
    @NotBlank
    @Length(max = 5)
    private String nickName;

    /**
     * 登录邮箱
     */
    @Email
    @NotBlank
    private String email;

    /**
     * 登录密码
     */
    @NotBlank
    @Length(max = 9)
    private String pwd;

    /**
     * 手机号
     */
    @PhoneNumber
    @NotBlank
    private String cellPhoneNum;

}
