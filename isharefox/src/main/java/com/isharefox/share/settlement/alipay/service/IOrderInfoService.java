package com.isharefox.share.settlement.alipay.service;

import com.isharefox.share.settlement.alipay.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-05
 */
public interface IOrderInfoService extends IService<OrderInfo> {
    public void pushPassword2Clinet(String orderId);
}
