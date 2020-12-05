package com.isharefox.share.user;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.isharefox.share.auth.ShiroUtil;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Api(value = "用户页面导航")
@Controller
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class NavigationController {
    final DefaultKaptcha defaultKaptcha;
    public String genKaptcha() {
        String kaptcha = defaultKaptcha.createText();
        ShiroUtil.currentSession().setAttribute("kaptcha", kaptcha);
        BufferedImage image = defaultKaptcha.createImage(kaptcha);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, "png", byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }

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
    @GetMapping("/index**")
    public String userIndex(Model model) {
        model.addAttribute("user", SecurityUtils.getSubject().getPrincipal());
        return "user/item/index";
    }

    /**
     * 商品列表页
     */
    @GetMapping("/item/list")
    public String userList(Model model) {
        model.addAttribute("user", SecurityUtils.getSubject().getPrincipal());
        return "user/item/index";
    }

    /**
     * 新增商品页面
     */
    @GetMapping("/item/add")
    public String itemAdd(Model model) {
        model.addAttribute("user", SecurityUtils.getSubject().getPrincipal());
        return "user/item/add";
    }
}
