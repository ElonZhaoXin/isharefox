package com.isharefox.share.trade.pay;


import com.isharefox.share.trade.pay.alipay.model.TradePrecreateReqeust;
import com.isharefox.share.trade.pay.alipay.model.TradePrecreateResponse;
import com.isharefox.share.trade.pay.alipay.model.TradeQueryRequest;
import com.isharefox.share.trade.pay.alipay.model.TradeQueryResponse;

public interface PayService {
    /**
     * 创建二维码支付订单，返回二维码
     * @param reqeust
     * @return
     */
    TradePrecreateResponse tradePrecreate(TradePrecreateReqeust reqeust);

    TradeQueryResponse tradeQuery(TradeQueryRequest request);
}