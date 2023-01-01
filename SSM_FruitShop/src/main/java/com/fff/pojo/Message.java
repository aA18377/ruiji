package com.fff.pojo;


public class Message {

  private long id;
  private String name;
  private String phone;
  private String content;

  public Message(long id, String name, String phone, String content) {
    this.id = id;
    this.name = name;
    this.phone = phone;
    this.content = content;
  }

  public Message() {
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


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
