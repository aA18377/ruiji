package com.fff.pojo;


public class User {

  private long id;
  private String userName;
  private String passWord;
  private String phone;
  private String realName;
  private String sex;
  private String address;
  private String email;

  public User(long id, String userName, String passWord, String phone, String realName, String sex, String address, String email) {
    this.id = id;
    this.userName = userName;
    this.passWord = passWord;
    this.phone = phone;
    this.realName = realName;
    this.sex = sex;
    this.address = address;
    this.email = email;
  }

  public User() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getPassWord() {
    return passWord;
  }

  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
