package com.yanmou.mybatisplusstudy.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AddressBook {
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  @TableField(fill = FieldFill.INSERT)
  private Long id;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private long userId;
  private String consignee;
  private long sex;
  private String phone;
  private String provinceCode;
  private String provinceName;
  private String cityCode;
  private String cityName;
  private String districtCode;
  private String districtName;
  private String detail;
  private String label;
  private long isDefault;
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
