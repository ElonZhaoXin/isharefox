package com.isharefox.share.item.service.impl;

import com.isharefox.share.item.entity.Item;
import com.isharefox.share.item.mapper.ItemMapper;
import com.isharefox.share.item.service.IItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoxin
 * @since 2020-11-29
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

}
