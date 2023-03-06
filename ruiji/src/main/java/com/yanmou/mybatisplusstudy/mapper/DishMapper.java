package com.yanmou.mybatisplusstudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanmou.mybatisplusstudy.pojo.Dish;
import com.yanmou.mybatisplusstudy.pojo.DishFlavor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2023/2/26 - 02 - 26 - 16:35
 * @Description: com.yanmou.mybatisplusstudy.mapper
 * @version: 1.0
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {


    List<DishFlavor> getUpdateData(@Param("id") long id);
}
