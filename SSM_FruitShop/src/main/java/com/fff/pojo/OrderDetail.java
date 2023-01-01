package com.fff.pojo;


public class OrderDetail {

  private long id;
  private long itemId;
  private long orderId;
  /**
   * 0.未退货 1已退货
   */
  private long status;
  private long num;
  private String total;
  private Item item;

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public OrderDetail(long id, long itemId, long orderId, long status, long num, String total, Item item) {
    this.id = id;
    this.itemId = itemId;
    this.orderId = orderId;
    this.status = status;
    this.num = num;
    this.total = total;
    this.item = item;
  }

  public OrderDetail() {
  }

  public long getItemId() {
    return itemId;
  }

  public void setItemId(long itemId) {
    this.itemId = itemId;
  }

  @Override
  public String toString() {
    return "OrderDetail{" +
            "id=" + id +
            ", itemId=" + itemId +
            ", orderId=" + orderId +
            ", status=" + status +
            ", num=" + num +
            ", total='" + total + '\'' +
            ", item=" + item +
            '}';
  }

  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public long getNum() {
    return num;
  }

  public void setNum(long num) {
    this.num = num;
  }


  public String getTotal() {
    return total;
  }

  public void setTotal(String total) {
    this.total = total;
  }

}
