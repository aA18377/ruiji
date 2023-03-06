package com.yanmou.mybatisplusstudy.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Dish {
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  @TableField
  private Long id;
  private String name;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private long categoryId;
  private double price;
  private String code;
  private String image;
  private String description;
  private long status;
  private long sort;
  @TableField(fill = FieldFill.INSERT)
  @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
  private java.sql.Timestamp createTime;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
  private java.sql.Timestamp updateTime;
  @TableField(fill = FieldFill.INSERT)
  private long createUser;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private long updateUser;
  @TableField(fill = FieldFill.INSERT)
  private long isDeleted;


}
