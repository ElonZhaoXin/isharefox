package com.isharefox.share.web.controller.dto;

import com.isharefox.share.item.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
