package com.isharefox.share.trade.dto;

import com.isharefox.share.settlement.alipay.entity.OrderInfo;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemPayDto {
    /**
     * 支付二维码 base64
     */
    private String qrCodeBase64;

    /**
     * 前端页面请求后端的websocket
     */
    private String webSocketUrl;

    /**
     * 订单信息
     */
    private OrderInfo orderInfo;
}
