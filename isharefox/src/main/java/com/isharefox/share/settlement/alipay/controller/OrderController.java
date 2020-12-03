package com.isharefox.share.settlement.alipay.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhaoxin
 * @since 2020-11-30
 */
@RestController
@RequestMapping("/settlement.alipay/order")
public class OrderController {
	
	/**
	 * alipay 当面付，异步回调
	 * @param order
	 */
	@PostMapping("/callback")
	public void faceToFaceCallback(OrderDTO order) {
		System.out.println("get callback");
		System.out.println(order.toString());
	}
}

