/**
 * 
 */
package com.isharefox.share.trade.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoxin
 * 交易控制器
 */
@RestController
@RequestMapping("/trade")
public class TradeController {
	/**
	 * 获取支付二维码
	 * @param order
	 */
	@PostMapping("/qrcode")
	public String getQrcode() {
		return null;
	}
	
	/**
	 * 查询支付结果
	 * @param order
	 */
	@PostMapping("/pay/result")
	public String signIn() {
		return null;
	}
}
