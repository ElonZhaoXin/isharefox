package com.isharefox.share.settlement.alipay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isharefox.share.item.entity.Item;
import com.isharefox.share.item.service.IItemService;
import com.isharefox.share.settlement.alipay.entity.OrderInfo;
import com.isharefox.share.settlement.alipay.mapper.OrderInfoMapper;
import com.isharefox.share.settlement.alipay.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.isharefox.share.ws.WebSocketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-05
 */
@Service
@AllArgsConstructor
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

   final IItemService iItemService;

    @Override
    public void pushPassword2Clinet(String orderId) {
        OrderInfo orderInfo = getOne(new QueryWrapper<OrderInfo>().lambda().eq(OrderInfo::getOutTradeNo, orderId));
        Item item = iItemService.getOne(new QueryWrapper<Item>().lambda().eq(Item::getResourceId, orderInfo.getResourceId()));
        WebSocketService.pushMessage(orderId, item.getZipPwd());
    }
}
