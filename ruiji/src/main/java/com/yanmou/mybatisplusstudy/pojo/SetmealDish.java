package com.yanmou.mybatisplusstudy.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

@Component
public class SetmealDish {
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  @TableId
  private Long id;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private String setmealId;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private String dishId;
  private String name;
  private double price;
  private long copies;
  private long sort;
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


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getSetmealId() {
    return setmealId;
  }

  public void setSetmealId(String setmealId) {
    this.setmealId = setmealId;
  }


  public String getDishId() {
    return dishId;
  }

  public void setDishId(String dishId) {
    this.dishId = dishId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public long getCopies() {
    return copies;
  }

  public void setCopies(long copies) {
    this.copies = copies;
  }


  public long getSort() {
    return sort;
  }

  public void setSort(long sort) {
    this.sort = sort;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }


  public long getCreateUser() {
    return createUser;
  }

  public void setCreateUser(long createUser) {
    this.createUser = createUser;
  }


  public long getUpdateUser() {
    return updateUser;
  }

  public void setUpdateUser(long updateUser) {
    this.updateUser = updateUser;
  }


  public long getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(long isDeleted) {
    this.isDeleted = isDeleted;
  }

}
