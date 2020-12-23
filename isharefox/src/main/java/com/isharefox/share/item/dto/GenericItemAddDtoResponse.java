package com.isharefox.share.item.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.isharefox.share.common.api.BaseResponse;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GenericItemAddDtoResponse extends BaseResponse {
    /**
     * 生成的资源编号
     */
    private String resourceId;
}
