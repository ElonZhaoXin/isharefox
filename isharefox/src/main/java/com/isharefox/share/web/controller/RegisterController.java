package com.isharefox.share.web.controller;


import com.isharefox.share.user.regist.entity.User;
import com.isharefox.share.user.regist.service.IUserService;
import com.isharefox.share.web.controller.dto.UserDto;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Api(value = "签约服务")
@Controller
@RequestMapping("/register")
@AllArgsConstructor
@Slf4j
public class RegisterController {

    final ModelMapper modelMapper;

    final IUserService userService;

    @PostMapping
    public String regist(@RequestParam UserDto userDto, Model model) {
        User newUser = modelMapper.map(userDto, User.class);
        newUser.setStatus("1");
        boolean success = userService.save(newUser);
        if (success) {
            //成功，登录页
            return "redirect:/login";
        } else {
            //
            return "/user/index";
        }
    }
}
