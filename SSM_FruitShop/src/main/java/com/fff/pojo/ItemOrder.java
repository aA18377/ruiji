package com.fff.pojo;


import java.sql.Timestamp;
import java.util.List;

public class ItemOrder {

  private long id;
  private long itemId;
  private long userId;
  private String code;
  private java.sql.Timestamp addTime;
  private String total;
  private long isDelete;
  /**
   * 0.新建待发货1.已取消 2已发货3.到收货4已评价
   */
  private long status;
  private User user;

  private List<OrderDetail> details;


  public ItemOrder(long id, long itemId, long userId, String code, Timestamp addTime, String total, long isDelete, long status, User user, List<OrderDetail> details) {
    this.id = id;
    this.itemId = itemId;
    this.userId = userId;
    this.code = code;
    this.addTime = addTime;
    this.total = total;
    this.isDelete = isDelete;
    this.status = status;
    this.user = user;
    this.details = details;
  }


  public List<OrderDetail> getDetails() {
    return details;
  }

  public void setDetails(List<OrderDetail> details) {
    this.details = details;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public ItemOrder() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getItemId() {
    return itemId;
  }

  public void setItemId(long itemId) {
    this.itemId = itemId;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public java.sql.Timestamp getAddTime() {
    return addTime;
  }

  public void setAddTime(java.sql.Timestamp addTime) {
    this.addTime = addTime;
  }


  public String getTotal() {
    return total;
  }

  public void setTotal(String total) {
    this.total = total;
  }


  public long getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(long isDelete) {
    this.isDelete = isDelete;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "ItemOrder{" +
            "id=" + id +
            ", itemId=" + itemId +
            ", userId=" + userId +
            ", code='" + code + '\'' +
            ", addTime=" + addTime +
            ", total='" + total + '\'' +
            ", isDelete=" + isDelete +
            ", status=" + status +
            ", user=" + user +
            ", details=" + details +
            '}';
  }
}
