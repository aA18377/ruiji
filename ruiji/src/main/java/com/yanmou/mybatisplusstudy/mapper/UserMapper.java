package com.yanmou.mybatisplusstudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanmou.mybatisplusstudy.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: zhaoss
 * @Date: 2023/3/3 - 03 - 03 - 19:43
 * @Description: com.yanmou.mybatisplusstudy.mapper
 * @version: 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
