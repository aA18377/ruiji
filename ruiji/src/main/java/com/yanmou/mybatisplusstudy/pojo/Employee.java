package com.yanmou.mybatisplusstudy.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@TableName("employee")
public class Employee {

  @JsonFormat(shape = JsonFormat.Shape.STRING)
  @TableId
  private Long id;
  private String name;
  private String username;
  private String password;
  private String phone;
  private String sex;
  private String idNumber;
  private long status;
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




}
