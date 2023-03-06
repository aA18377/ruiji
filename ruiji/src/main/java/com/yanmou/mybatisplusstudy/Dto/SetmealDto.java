package com.yanmou.mybatisplusstudy.Dto;

import com.yanmou.mybatisplusstudy.pojo.Setmeal;
import com.yanmou.mybatisplusstudy.pojo.SetmealDish;
import lombok.Data;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2023/3/2 - 03 - 02 - 18:41
 * @Description: com.yanmou.mybatisplusstudy.Dto
 * @version: 1.0
 */
@Data
public class SetmealDto extends Setmeal {
    private List<SetmealDish> setmealDishes;
}
