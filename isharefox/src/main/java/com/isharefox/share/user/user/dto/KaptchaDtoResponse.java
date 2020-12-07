package com.isharefox.share.user.user.dto;

import com.isharefox.share.common.api.BaseResponse;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class KaptchaDtoResponse extends BaseResponse {
    private String urlBase64;
}
