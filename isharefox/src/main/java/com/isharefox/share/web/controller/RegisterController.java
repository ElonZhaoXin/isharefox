package com.isharefox.share.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isharefox.share.common.api.BaseResponse;
import com.isharefox.share.user.regist.entity.User;
import com.isharefox.share.user.regist.service.IUserService;
import com.isharefox.share.web.controller.dto.UserDto;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Api(value = "签约服务")
@RestController
@RequestMapping("/register")
@AllArgsConstructor
@Slf4j
public class RegisterController {

    final ModelMapper modelMapper;

    final IUserService userService;

    @PostMapping
    public BaseResponse regist(@RequestBody UserDto userDto, Model model) {
        User newUser = modelMapper.map(userDto, User.class);
        User one = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getEmail, userDto.getEmail()));
        if (one != null) {
            return BaseResponse.builder()
                    .message("用户已注册，请更换email后重试!")
                    .build();
        }
        newUser.setStatus("1");
        boolean success = userService.save(newUser);
        return BaseResponse.builder()
                .message(success ? "注册成功" : "注册失败")
                .build();
    }
}
