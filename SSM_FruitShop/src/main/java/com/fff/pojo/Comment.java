package com.fff.pojo;


import java.sql.Timestamp;

public class Comment {

  private long id;
  private long userId;
  private User user;
  private long itemId;
  private String content;
  private java.sql.Timestamp addTime;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Comment(long id, long userId, User user, long itemId, String content, Timestamp addTime) {
    this.id = id;
    this.userId = userId;
    this.user = user;
    this.itemId = itemId;
    this.content = content;
    this.addTime = addTime;
  }

  public Comment() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getItemId() {
    return itemId;
  }

  public void setItemId(long itemId) {
    this.itemId = itemId;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public java.sql.Timestamp getAddTime() {
    return addTime;
  }

  public void setAddTime(java.sql.Timestamp addTime) {
    this.addTime = addTime;
  }

}
