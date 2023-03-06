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
public class Orders {
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  @TableField(fill = FieldFill.INSERT)
  @TableId(type = IdType.ASSIGN_ID)
  private Long id;
  private String number;
  //订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
  private long status;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private long userId;
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private long addressBookId;
  @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
  private java.sql.Timestamp orderTime;
  //结账时间
  @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
  private java.sql.Timestamp checkoutTime;
  //支付方式 1微信,2支付宝
  private long payMethod;
  private double amount;
  //备注
  private String remark;
  private String phone;
  private String address;
  private String userName;
  //收货人名字
  private String consignee;



}
