package com.isharefox.share.web.controller.user;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "用户页面导航")
@Controller
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class NavigationController {

    @GetMapping("/index")
    public String userIndex(Model model) {
        model.addAttribute("name", SecurityUtils.getSubject().getPrincipal());
        return "/user/index";
    }
}
