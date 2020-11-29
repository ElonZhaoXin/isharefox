package com.isharefox.share.user.service.impl;

import com.isharefox.share.user.entity.User;
import com.isharefox.share.user.mapper.UserMapper;
import com.isharefox.share.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoxin
 * @since 2020-11-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
