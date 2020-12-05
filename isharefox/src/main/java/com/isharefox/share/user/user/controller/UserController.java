package com.isharefox.share.user.user.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.isharefox.share.kernel.util.ConvertUtils;
import com.isharefox.share.settlement.alipay.Alipay;
import com.isharefox.share.settlement.alipay.controller.OrderDTO;
import com.isharefox.share.settlement.alipay.entity.OrderInfo;
import com.isharefox.share.user.user.entity.User;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-05
 */
@RestController
@RequestMapping("/user")
public class UserController {


	/**
	 * 用户登录
	 * @param order
	 */
	@PostMapping("/signin")
	public String signIn(User user) {
		return null;
	}
}

