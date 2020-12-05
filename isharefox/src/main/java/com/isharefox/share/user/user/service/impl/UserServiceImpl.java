package com.isharefox.share.user.user.service.impl;

import com.isharefox.share.user.user.entity.User;
import com.isharefox.share.user.user.mapper.UserMapper;
import com.isharefox.share.user.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaoxin
 * @since 2020-12-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
