package com.yanmou.mybatisplusstudy.Dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yanmou.mybatisplusstudy.pojo.Category;
import com.yanmou.mybatisplusstudy.pojo.Dish;
import com.yanmou.mybatisplusstudy.pojo.DishFlavor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2023/2/26 - 02 - 26 - 17:19
 * @Description: com.yanmou.mybatisplusstudy.Dto
 * @version: 1.0
 */
@Data
public class DishDto extends Dish implements Serializable {
    private static final long serialVersionUID = 3L;
    @TableField(exist = false)
    private String categoryName;
    @TableField(exist = false)
    private List<DishFlavor> flavors;
    @TableField(exist = false)
    private List<Category> categoryList;

}
