package com.yanmou.mybatisplusstudy.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ShoppingCart {
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  @TableId
  private Long id;
  private String name;
  private String image;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private long userId;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private long dishId;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private long setmealId;
  private String dishFlavor;
  private long number;
  private double amount;
  @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
  @TableField(fill = FieldFill.INSERT)
  private java.sql.Timestamp createTime;



}
