package com.isharefox.share.web.controller.dto;
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
public class UserDto {
    /**
     * 昵称
     */
    @NotBlank
    @Length(max = 5)
    private String nickName;

    /**
     * email
     */
    @Email
    @NotBlank
    private String email;

    /**
     * 密码
     */
    @NotBlank
    @Length(max = 9)
    private String password;

    /**
     * 电话号码
     */
    @PhoneNumber
    @NotBlank
    private String phone;

}
