package com.isharefox.share.user.register.dto;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserLoginDto {
    @Email
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String kaptcha;
}
