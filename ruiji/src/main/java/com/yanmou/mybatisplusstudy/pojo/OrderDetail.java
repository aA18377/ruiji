package com.yanmou.mybatisplusstudy.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class OrderDetail {
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  @TableField(fill = FieldFill.INSERT)
  @TableId
  private Long id;
  //菜品或者套餐名
  private String name;
  private String image;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private long orderId;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private long dishId;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private long setmealId;
  private String dishFlavor;
  private long number;
  private double amount;




}
