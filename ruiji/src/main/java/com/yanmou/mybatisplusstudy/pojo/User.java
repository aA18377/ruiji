package com.yanmou.mybatisplusstudy.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class User {
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  @TableId
  private Long id;
  private String name;
  private String phone;
  private String sex;
  private String idNumber;
  private String avatar;
  private long status;




}
