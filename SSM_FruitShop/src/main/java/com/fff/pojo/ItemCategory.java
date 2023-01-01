package com.fff.pojo;


public class ItemCategory {

  /**
   * 种类-->品种--->品牌
   */
  private long id;
  private String name;
  private long pid;
  private long isDelete;

  public ItemCategory(long id, String name, long pid, long isDelete) {
    this.id = id;
    this.name = name;
    this.pid = pid;
    this.isDelete = isDelete;
  }

  public ItemCategory() {
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


  public long getPid() {
    return pid;
  }

  public void setPid(long pid) {

    this.pid = pid;
  }


  public long getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(long isDelete) {
    this.isDelete = isDelete;
  }

}
