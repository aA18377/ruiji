package com.yanmou.mybatisplusstudy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanmou.mybatisplusstudy.mapper.ShoppingCartMapper;
import com.yanmou.mybatisplusstudy.pojo.Dish;
import com.yanmou.mybatisplusstudy.pojo.DishFlavor;
import com.yanmou.mybatisplusstudy.pojo.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2023/3/4 - 03 - 04 - 17:50
 * @Description: com.yanmou.mybatisplusstudy.service.impl
 * @version: 1.0
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> {
    @Autowired
    private DishServiceImpl dishService;
    @Autowired
    private DishFlavorImpl dishFlavorService;
    public List<DishFlavor> getDishFlavor(ShoppingCart cart){
        QueryWrapper<Dish> wrapper = new QueryWrapper<>();
        wrapper.eq("name",cart.getName());
        long dishId = dishService.getOne(wrapper).getId();
        QueryWrapper<DishFlavor> dishFlavorQueryWrapper = new QueryWrapper<>();
        dishFlavorQueryWrapper.eq("dish_id",dishId);
        return dishFlavorService.list(dishFlavorQueryWrapper);
    }
}
