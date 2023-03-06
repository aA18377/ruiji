package com.yanmou.mybatisplusstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanmou.mybatisplusstudy.mapper.DishMapper;
import com.yanmou.mybatisplusstudy.pojo.Dish;
import com.yanmou.mybatisplusstudy.pojo.DishFlavor;
import com.yanmou.mybatisplusstudy.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2023/2/26 - 02 - 26 - 16:36
 * @Description: com.yanmou.mybatisplusstudy.service.impl
 * @version: 1.0
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    DishMapper mapper;

    public List<DishFlavor> getUpdateData(long id) {
        return mapper.getUpdateData(id);
    }
}
