package com.isharefox.share.settlement.alipay.service.impl;

import com.isharefox.share.settlement.alipay.entity.Order;
import com.isharefox.share.settlement.alipay.mapper.OrderMapper;
import com.isharefox.share.settlement.alipay.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
