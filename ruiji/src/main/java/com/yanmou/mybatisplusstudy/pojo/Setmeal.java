package com.yanmou.mybatisplusstudy.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Setmeal {
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  @TableId
  private Long id;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private long categoryId;
  private String name;
  private double price;
  private long status;
  private String code;
  private String description;
  private String image;
  @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
  @TableField(fill = FieldFill.INSERT)
  private java.sql.Timestamp createTime;
  @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private java.sql.Timestamp updateTime;
  @TableField(fill = FieldFill.INSERT)
  private long createUser;
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private long updateUser;
  @TableField(fill = FieldFill.INSERT)
  private long isDeleted;



}
