package com.isharefox.share.web.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isharefox.share.common.api.BaseResponse;
import com.isharefox.share.common.util.ItemIdUtil;
import com.isharefox.share.item.entity.Item;
import com.isharefox.share.item.service.IItemService;
import com.isharefox.share.web.auth.ShiroUtil;
import com.isharefox.share.web.controller.dto.GenericItemDtoResponse;
import com.isharefox.share.web.controller.dto.ItemAddDto;
import com.isharefox.share.web.controller.dto.ItemDeleteDto;
import com.isharefox.share.web.controller.dto.ItemDto;
import com.isharefox.share.web.property.EnvProperties;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "商品接口")
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/user/item")
public class ItemController {
    final IItemService iItemService;
    final ModelMapper modelMapper;
    final EnvProperties envProperties;

    @PostMapping("/list")
    public GenericItemDtoResponse list(@RequestParam long current, @RequestParam long size) {
        Integer userId = ShiroUtil.currentUser().getId();
        Page<Item> queryPage = new Page<>(current, size);
        Page<Item> page = iItemService.page(queryPage,
                new QueryWrapper<Item>().lambda()
                        .eq(Item::getUserId, userId)
                        .orderByDesc(Item::getId));
        IPage<ItemDto> itemDtoIPage = page.convert((item) -> {
            ItemDto itemDto = modelMapper.map(item, ItemDto.class);
            itemDto.setLinkUrl(envProperties.getDomain() + "/item/" + ItemIdUtil.enCode(item.getId()));
            return itemDto;
        });
        return new GenericItemDtoResponse(itemDtoIPage);
    }

    @PostMapping("/add")
    public BaseResponse insert(@RequestBody @Validated ItemAddDto itemAddDto) {
        Item item = modelMapper.map(itemAddDto, Item.class);
        item.setStatus("1");
        item.setUserId(ShiroUtil.currentUser().getId());
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
