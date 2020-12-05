package com.isharefox.share.settlement.alipay.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.isharefox.share.kernel.util.ConvertUtils;
import com.isharefox.share.settlement.alipay.Alipay;
import com.isharefox.share.settlement.alipay.entity.OrderInfo;
import com.isharefox.share.settlement.alipay.mapper.OrderInfoMapper;

/**
 * <p>
 *  订单控制器
 *  alipay 当面付异步通知路径：/settlement/alipay/order/callback
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-05
 */
@RestController
@RequestMapping("/settlement/alipay/order")
public class OrderController {
	
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	
	/**
	 * alipay 当面付，异步回调
	 * @param order
	 */
	@PostMapping("/callback")
	public String faceToFaceCallback(OrderDTO orderDto) {
		System.out.println("接收到alipay异步回调请求：" + orderDto.toString());
		boolean verifySuccess = Alipay.FaceToFace.verifyNotify(orderDto);
		if(verifySuccess) {
			OrderInfo order = ConvertUtils.lowerUnderScopeToLowerCamel(orderDto, OrderInfo.class);
			
			UpdateWrapper<OrderInfo> updateWrapper = new UpdateWrapper<OrderInfo>();
			updateWrapper.eq("out_trade_no", order.getOutTradeNo());
			updateWrapper.eq("total_amount", order.getTotalAmount());
			
			int updResult = orderInfoMapper.update(order, updateWrapper);
			if(0 == updResult) {
				return "success";
			}
			return "金额或者订单号不对，找不到记录。";
			
		} else {
			System.out.println("接收到alipay异步回调请求,且验签失败。危险，危险！");
			return "验签失败";
		}
	}
}

