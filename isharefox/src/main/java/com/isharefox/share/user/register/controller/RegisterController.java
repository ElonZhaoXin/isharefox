package com.isharefox.share.user.register.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isharefox.share.common.api.BaseResponse;
import com.isharefox.share.common.api.ResultCode;
import com.isharefox.share.common.util.IdGeneratoer;
import com.isharefox.share.user.register.dto.UserRegistDto;
import com.isharefox.share.user.user.entity.User;
import com.isharefox.share.user.user.service.IUserService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@Api(value = "签约服务")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class RegisterController {

    final ModelMapper modelMapper;

    final IUserService userService;

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
        newUser.setCreateTime(LocalDateTime.now());
        newUser.setUpdateTime(LocalDateTime.now());

        boolean success = userService.save(newUser);
        return BaseResponse.builder()
                .code(success ? ResultCode.SUCCESS : ResultCode.FAILURE)
                .message(success ? "注册成功" : "注册失败")
                .build();
    }
}
