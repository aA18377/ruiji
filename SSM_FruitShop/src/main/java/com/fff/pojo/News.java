package com.fff.pojo;


import java.sql.Timestamp;

public class News {

  private long id;
  private String name;
  private String content;
  private java.sql.Timestamp addTime;

  public News(long id, String name, String content, Timestamp addTime) {
    this.id = id;
    this.name = name;
    this.content = content;
    this.addTime = addTime;
  }

  public News() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
