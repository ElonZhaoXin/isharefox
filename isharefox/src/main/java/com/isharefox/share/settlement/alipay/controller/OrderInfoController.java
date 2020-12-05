package com.isharefox.share.settlement.alipay.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.isharefox.share.kernel.util.ConvertUtils;
import com.isharefox.share.settlement.alipay.entity.OrderInfo;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-05
 */
@RestController
@RequestMapping("/settlement.alipay/order-info")
public class OrderInfoController {
	/**
	 * alipay 当面付，异步回调
	 * @param order
	 */
	@PostMapping("/callback")
	public void faceToFaceCallback(OrderDTO orderDto) {
		System.out.println("接收到alipay异步回调请求：" + orderDto.toString());
		OrderInfo order = ConvertUtils.lowerUnderScopeToLowerCamel(orderDto, OrderInfo.class);
		order.setUserId("001");
		order.insert();
	}
}

