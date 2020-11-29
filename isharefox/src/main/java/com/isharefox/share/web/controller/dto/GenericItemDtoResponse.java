package com.isharefox.share.web.controller.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.isharefox.share.common.api.BaseResponse;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GenericItemDtoResponse extends BaseResponse {
    private IPage<ItemDto> data;
}
