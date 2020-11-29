package com.isharefox.share.web.controller.user;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.security.auth.Subject;

@Api(value = "用户页面导航")
@Controller
@AllArgsConstructor
@Slf4j
public class NavigationController {

    @RequestMapping("/login")
    public String loginSuccess(Model model) {
        if(SecurityUtils.getSubject().isAuthenticated()) {
            log.info("用户已登录，跳转到用户页面");
            //这里不加前缀/，加了在服务器上报错，找不到template
            return "user/item/index";
        }
        log.info("用户未登录，跳转登录页面");
        return "redirect:/login.html";
    }

    /**
     * 用户首页
     */
    @GetMapping("/user/index**")
    public String userIndex(Model model) {
        model.addAttribute("user", SecurityUtils.getSubject().getPrincipal());
        return "user/item/index";
    }

    /**
     * 商品列表页
     */
    @GetMapping("/user/item/list")
    public String userList(Model model) {
        model.addAttribute("user", SecurityUtils.getSubject().getPrincipal());
        return "user/item/index";
    }

    /**
     * 新增商品页面
     */
    @GetMapping("/user/item/add")
    public String itemAdd(Model model) {
        model.addAttribute("user", SecurityUtils.getSubject().getPrincipal());
        return "user/item/add";
    }
}
