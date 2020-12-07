package com.isharefox.share.trade.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemPayDto {
    /**
     * 支付二维码 base64
     */
    private String qrCodeBase64;
}
