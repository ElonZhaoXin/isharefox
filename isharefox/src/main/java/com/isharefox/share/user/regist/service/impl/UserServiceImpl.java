package com.isharefox.share.user.regist.service.impl;

import com.isharefox.share.user.regist.entity.User;
import com.isharefox.share.user.regist.mapper.UserMapper;
import com.isharefox.share.user.regist.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author isharefox
 * @since 2020-11-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
