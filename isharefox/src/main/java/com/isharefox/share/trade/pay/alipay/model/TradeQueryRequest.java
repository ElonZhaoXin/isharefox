package com.isharefox.share.trade.pay.alipay.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TradeQueryRequest {
    /**
     * 订单号
     */
    private String orderId;
}
