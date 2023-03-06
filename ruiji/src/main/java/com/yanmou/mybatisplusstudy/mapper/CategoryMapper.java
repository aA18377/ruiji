package com.yanmou.mybatisplusstudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanmou.mybatisplusstudy.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: zhaoss
 * @Date: 2023/2/25 - 02 - 25 - 20:33
 * @Description: com.yanmou.mybatisplusstudy.mapper
 * @version: 1.0
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
