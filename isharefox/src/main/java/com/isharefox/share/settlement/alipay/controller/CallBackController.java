package com.isharefox.share.settlement.alipay.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.isharefox.share.settlement.alipay.dto.OrderDTO;
import com.isharefox.share.settlement.alipay.service.IOrderInfoService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import com.isharefox.share.kernel.util.ConvertUtils;
import com.isharefox.share.settlement.alipay.Alipay;
import com.isharefox.share.settlement.alipay.entity.OrderInfo;

import java.util.Map;

/**
 *
 * https://opendocs.alipay.com/open/194/103296
 * @see com.isharefox.share.settlement.alipay.Alipay 面对面下单
 * @author zhaoxin
 * @since 2020-12-05
 */
@Api("支付宝异步回调")
@RestController
@RequestMapping("/share/settlement/alipay/callback")
@Slf4j
@AllArgsConstructor
public class CallBackController {

	final IOrderInfoService orderInfoService;

	/**
	 * alipay 当面付，异步回调
	 * @param callBackMap 回调参数，用作验签
	 * @param orderDTO 对时间戳、金额等参数进行转换
	 * @return
	 */
	@PostMapping("/facetoface")
	public String faceToFaceCallback(@RequestParam Map<String, String> callBackMap, OrderDTO orderDTO) {
		log.info("接收到alipay异步回调请求：{}", new Gson().toJson(callBackMap));
		boolean verifySuccess = Alipay.FaceToFace.verifyNotify(callBackMap);
		if(verifySuccess) {
			log.info("支付宝验签通过");
			OrderInfo order = ConvertUtils.lowerUnderScopeToLowerCamel(orderDTO, OrderInfo.class);
			order.setStatus("1");
			boolean update = orderInfoService.update(order, new QueryWrapper<OrderInfo>().lambda()
					.and(i -> i.eq(OrderInfo::getOutTradeNo, order.getOutTradeNo())
							.eq(OrderInfo::getTotalAmount, order.getTotalAmount())));
			if (update) {
				orderInfoService.pushPassword2Clinet(order.getOutTradeNo());
				return "success";
			} else {
				log.error("alipay异步回调请求更新失败");
				return "updateError";
			}
		} else {
			log.error("接收到alipay异步回调请求,验签失败。危险，危险！");
			return "verifyError";
		}
	}
}

