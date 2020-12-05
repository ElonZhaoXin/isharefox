/**
 * 
 */
package com.isharefox.share.trade.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isharefox.share.item.entity.Item;
import com.isharefox.share.item.mapper.ItemMapper;
import com.isharefox.share.settlement.alipay.Alipay;
import com.isharefox.share.settlement.alipay.entity.OrderInfo;
import com.isharefox.share.settlement.alipay.mapper.OrderInfoMapper;

/**
 * @author zhaoxin
 * 交易控制器
 */
@RestController
@RequestMapping("/share")
public class TradeController {
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	
	/**
	 * 获取支付二维码
	 * http://isharefox.com/share/1234567
	 * @param resouceId 资源编号
	 */
	@RequestMapping(value="/*",method=RequestMethod.GET)
	public void getQrcode(HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		String resourceId = requestUri.substring(requestUri.lastIndexOf("/")+1);	
		
		// 根据resourceId查询商品表中的相关信息
		QueryWrapper<Item> queryWrapper = new QueryWrapper<Item>();
		queryWrapper.eq("resource_id", resourceId);
		Item item = itemMapper.selectOne(queryWrapper);
		Assert.notNull(item, "此商品[" + resourceId + "]还没有发布，请确认URL是否正确");
		
		// 生成64位订单号，插入订单库，并调用支付宝形成二维码
		String tradeNo = TradeUtil.getTradeNo(resourceId);
		
		OrderInfo order = new OrderInfo();
		order.setOutTradeNo(tradeNo);
		order.setUserId(item.getUserId());
		order.setResourceId(resourceId);
		orderInfoMapper.insert(order);
		
		
		String qrcode = Alipay.FaceToFace.getQrCode(item.getDescription(), tradeNo, item.getAmount().toString());
		
		// 跳转展码页面
	}
	
}
