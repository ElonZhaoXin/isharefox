/**
 * 
 */
package com.isharefox.share.trade.controller;

import javax.servlet.http.HttpServletRequest;

import com.isharefox.share.common.constans.PageConstans;
import com.isharefox.share.common.property.AssetLoader;
import com.isharefox.share.common.qrcode.QrcodeUtils;
import com.isharefox.share.common.util.ImageBase64Util;
import com.isharefox.share.item.service.IItemService;
import com.isharefox.share.settlement.alipay.service.IOrderInfoService;
import com.isharefox.share.trade.dto.ItemPayDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

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
@Controller
@RequestMapping("/share")
@AllArgsConstructor
public class TradeController {

	final IItemService itemService;

	final IOrderInfoService orderInfoService;

	final AssetLoader assetLoader;
	/**
	 * 获取支付二维码
	 * http://isharefox.com/share/1234567
	 * @param resourceId 资源编号
	 */
	@GetMapping(value="/{resourceId}")
	public String getQrcode(@PathVariable("resourceId") String resourceId, Model model) {

		// 根据resourceId查询商品表中的相关信息
		Item item = itemService.getOne(new QueryWrapper<Item>().lambda().eq(Item::getResourceId, resourceId));
		Assert.notNull(item, "此商品[" + resourceId + "]还没有发布，请确认URL是否正确");
		
		// 生成64位订单号，插入订单库，并调用支付宝形成二维码
		String tradeNo = TradeUtil.getTradeNo(resourceId);
		
		OrderInfo order = new OrderInfo();
		order.setOutTradeNo(tradeNo);
		order.setUserId(item.getUserId());
		order.setResourceId(resourceId);
		orderInfoService.save(order);

		Alipay.init();
		//调用支付宝生成二维码支付链接
		String qrcode = Alipay.FaceToFace
				.getQrCode(item.getDescription(), tradeNo, item.getAmount().toString());
		//根据链接生成二维码图片
		String qrImageStringBase64 = QrcodeUtils.createQrcodeBase64(qrcode, assetLoader.getAliLogFile());
		ItemPayDto itemPayDto = ItemPayDto.builder().qrCodeBase64(ImageBase64Util.appendImageTypePrefix(qrImageStringBase64)).build();
		model.addAttribute(PageConstans.ATTRIBUTE_NAME, itemPayDto);
		// 跳转展码页面
		return "item-pay";
	}
	
}
