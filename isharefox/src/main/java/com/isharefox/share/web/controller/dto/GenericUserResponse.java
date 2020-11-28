package com.isharefox.share.web.controller.dto;

import com.isharefox.share.common.api.BaseResponse;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GenericUserResponse extends BaseResponse {
    private UserDto account;
}
