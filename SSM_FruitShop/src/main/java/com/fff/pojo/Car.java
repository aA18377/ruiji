package com.fff.pojo;


public class Car {

  private long id;
  private long itemId;
  private long userId;
  private long num;
  private double price;
  private String total;
  private Item item;

  public Car(long id, long itemId, long userId, long num, double price, String total, Item item) {
    this.id = id;
    this.itemId = itemId;
    this.userId = userId;
    this.num = num;
    this.price = price;
    this.total = total;
    this.item = item;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public Car() {
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


  public long getNum() {
    return num;
  }

  public void setNum(long num) {
    this.num = num;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public String getTotal() {
    return total;
  }

  public void setTotal(String total) {
    this.total = total;
  }

}
