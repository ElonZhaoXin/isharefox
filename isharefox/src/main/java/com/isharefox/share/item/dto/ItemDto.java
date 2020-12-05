package com.isharefox.share.item.dto;

import com.isharefox.share.item.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDto extends Item {
    /**
     * 资源链接
     */
    private String linkUrl;
}
