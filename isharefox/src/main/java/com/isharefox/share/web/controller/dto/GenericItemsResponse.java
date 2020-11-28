package com.isharefox.share.web.controller.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.isharefox.share.common.api.BaseResponse;
import com.isharefox.share.item.entity.Item;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GenericItemsResponse extends BaseResponse {
    private Page<Item> data;
}
