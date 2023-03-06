package com.yanmou.mybatisplusstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanmou.mybatisplusstudy.mapper.UserMapper;
import com.yanmou.mybatisplusstudy.pojo.User;
import com.yanmou.mybatisplusstudy.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Auther: zhaoss
 * @Date: 2023/3/3 - 03 - 03 - 19:44
 * @Description: com.yanmou.mybatisplusstudy.service.impl
 * @version: 1.0
 */
@Service
public class UserSeriveImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
