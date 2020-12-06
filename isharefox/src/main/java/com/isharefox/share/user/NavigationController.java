package com.isharefox.share.user;

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
    /**
     * 用户首页
     */
    @GetMapping("/index**")
    public String userIndex(Model model) {
        return "user/item/index";
    }

    /**
     * 商品列表页
     */
    @GetMapping("/item/list")
    public String userList(Model model) {
        return "user/item/index";
    }

    /**
     * 新增商品页面
     */
    @GetMapping("/item/add")
    public String itemAdd(Model model) {
        return "user/item/add";
    }
}
