package com.isharefox.share.web.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isharefox.share.common.api.BaseResponse;
import com.isharefox.share.item.entity.Item;
import com.isharefox.share.item.service.IItemService;
import com.isharefox.share.web.auth.ShiroUtil;
import com.isharefox.share.web.controller.dto.GenericItemsResponse;
import com.isharefox.share.web.controller.dto.ItemDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "商品接口")
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/user/item")
public class ItemController {
    final IItemService iItemService;
    final ModelMapper modelMapper;

    @PostMapping("/list")
    public GenericItemsResponse list(@RequestParam long current, @RequestParam long size) {
        Integer id = ShiroUtil.currentUser().getId();
        Page<Item> queryPage = new Page<>(current, size);
        Page<Item> page = iItemService.page(queryPage, new QueryWrapper<Item>().lambda().eq(Item::getUserId, id));
        return new GenericItemsResponse(page);
    }

    @PostMapping("/add")
    public BaseResponse insert(@RequestParam @Validated ItemDto itemDto) {
        Item shareResource = modelMapper.map(itemDto, Item.class);
        return BaseResponse.builder().build();
    }
}
