package com.isharefox.share.user.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.isharefox.share.auth.KaptchaUsernamePasswordToken;
import com.isharefox.share.auth.ShiroUtil;
import com.isharefox.share.common.api.BaseResponse;
import com.isharefox.share.common.api.ResultCode;
import com.isharefox.share.common.util.IdGeneratoer;
import com.isharefox.share.common.util.ImageBase64Util;
import com.isharefox.share.user.user.dto.KaptchaDtoResponse;
import com.isharefox.share.user.user.dto.UserLoginDto;
import com.isharefox.share.user.user.dto.UserRegistDto;
import com.isharefox.share.user.user.entity.User;
import com.isharefox.share.user.user.service.IUserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.IOException;


@Api(value = "登录注册服务")
@RestController
@Validated
@AllArgsConstructor
@Slf4j
public class RegisterController {

    final ModelMapper modelMapper;

    final IUserService userService;

    final DefaultKaptcha defaultKaptcha;

    @PostMapping("/login")
    public BaseResponse login(@RequestBody UserLoginDto userLoginDto) {
        KaptchaUsernamePasswordToken kaptchaUsernamePasswordToken = new KaptchaUsernamePasswordToken(userLoginDto.getUsername(),userLoginDto.getPassword(), userLoginDto.getKaptcha());
        Subject subject = SecurityUtils.getSubject();
        subject.login(kaptchaUsernamePasswordToken);
        return BaseResponse.builder().message("登陆成功").build();
    }

    @PostMapping("/register")
    public BaseResponse regist(@RequestBody UserRegistDto userRegistDto, Model model) {
        User one = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getEmail, userRegistDto.getEmail()));

        if (one != null) {
            return BaseResponse.builder()
                    .code(ResultCode.FAILURE)
                    .message("用户已注册，请更换email后重试!")
                    .build();
        }
        User newUser = modelMapper.map(userRegistDto, User.class);
        User maxUser = userService.getOne(new QueryWrapper<User>().lambda().orderByDesc(User::getId).last("limit 1"));
        newUser.setUserId(IdGeneratoer.increment32Num(maxUser != null ? maxUser.getUserId() : null));
        newUser.setStatus("1");

        boolean success = userService.save(newUser);
        return BaseResponse.builder()
                .code(success ? ResultCode.SUCCESS : ResultCode.FAILURE)
                .message(success ? "注册成功" : "注册失败")
                .build();
    }

    /**
     * 验证码
     * @return
     * @throws IOException
     */
    @RequestMapping("/kaptcha")
    public KaptchaDtoResponse genKaptcha() throws IOException {
        String kaptcha = defaultKaptcha.createText();
        ShiroUtil.putKaptcha(kaptcha);
        BufferedImage image = defaultKaptcha.createImage(kaptcha);
        return new KaptchaDtoResponse(ImageBase64Util.image2Base64(image));
    }
}
