package com.isharefox.share.web.controller.user;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.isharefox.share.common.validation.PhoneNumber;
import com.isharefox.share.user.regist.entity.User;
import com.isharefox.share.user.regist.service.IUserService;
import com.isharefox.share.web.controller.dto.GenericUserResponse;
import com.isharefox.share.web.controller.dto.UserDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @see com.isharefox.share.web.conf.SwaggerConfig Api doc
 */
@Api(value = "账户服务")
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;
    private final ModelMapper modelMapper;

    @GetMapping(path = "/")
    public GenericUserResponse getAccountByPhoneNumber(@RequestParam @PhoneNumber String phone) {
        User one = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getPhone, phone));
        return new GenericUserResponse(modelMapper.map(one, UserDto.class));
    }
}
