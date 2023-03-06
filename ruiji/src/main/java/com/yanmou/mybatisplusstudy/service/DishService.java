package com.yanmou.mybatisplusstudy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yanmou.mybatisplusstudy.pojo.Dish;
import com.yanmou.mybatisplusstudy.pojo.DishFlavor;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2023/2/26 - 02 - 26 - 16:36
 * @Description: com.yanmou.mybatisplusstudy.service
 * @version: 1.0
 */
public interface DishService extends IService<Dish> {
    List<DishFlavor> getUpdateData(long id);
}
