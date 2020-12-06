package com.isharefox.share.item.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isharefox.share.auth.ShiroUtil;
import com.isharefox.share.common.api.BaseResponse;
import com.isharefox.share.common.property.EnvProperties;
import com.isharefox.share.common.util.IdGeneratoer;
import com.isharefox.share.item.dto.GenericItemDtoResponse;
import com.isharefox.share.item.dto.ItemAddDto;
import com.isharefox.share.item.dto.ItemDeleteDto;
import com.isharefox.share.item.dto.ItemDto;
import com.isharefox.share.item.entity.Item;
import com.isharefox.share.item.service.IItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-01
 */
@RestController
@RequestMapping("/user/item")
@Validated
@RequiredArgsConstructor
public class ItemController {
    final IItemService iItemService;
    final ModelMapper modelMapper;
    final EnvProperties envProperties;

    @PostMapping("/list")
    public GenericItemDtoResponse list(@RequestParam long current, @RequestParam long size) {
        Page<Item> queryPage = new Page<>(current, size);
        Page<Item> page = iItemService.page(queryPage,
                new QueryWrapper<Item>().lambda()
                        .eq(Item::getUserId, ShiroUtil.currentUser().getUserId())
                        .orderByDesc(Item::getId));
        IPage<ItemDto> itemDtoIPage = page.convert((item) -> {
            ItemDto itemDto = modelMapper.map(item, ItemDto.class);
            //设置商品展示路径
            itemDto.setLinkUrl(envProperties.getDomain() + "/item/" + item.getResourceId());
            return itemDto;
        });
        return new GenericItemDtoResponse(itemDtoIPage);
    }

    @PostMapping("/add")
    public BaseResponse insert(@RequestBody @Validated ItemAddDto itemAddDto) {
        Item item = modelMapper.map(itemAddDto, Item.class);
        item.setStatus("1");
        item.setUserId(ShiroUtil.currentUser().getUserId());

        Item maxItem = iItemService.getOne(new QueryWrapper<Item>().lambda().orderByDesc(Item::getId).last("limit 1"));
        item.setResourceId(IdGeneratoer.increment32Num(maxItem != null ? maxItem.getResourceId() : null));
        item.setCreateTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        boolean result = iItemService.save(item);
        return BaseResponse.builder().message(result ? "新增商品成功" : "新增商品失败").build();
    }

    @PostMapping("/delete")
    public BaseResponse insert(@Validated @RequestBody ItemDeleteDto itemDeleteDto) {
        boolean result = iItemService.removeById(itemDeleteDto.getId());
        if (result) {
            return BaseResponse.builder().message("删除成功").build();
        }
        return BaseResponse.builder().message("删除失败").build();
    }
}

