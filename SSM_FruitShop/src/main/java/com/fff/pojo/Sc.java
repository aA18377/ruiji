package com.fff.pojo;

import org.springframework.stereotype.Component;

public class Sc {

  private long id;
  private long itemId;
  private Item item;
  private long userId;

  public Sc(long id, long itemId, Item item, long userId) {
    this.id = id;
    this.itemId = itemId;
    this.item = item;
    this.userId = userId;
  }

  public Sc() {
  }

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

}
