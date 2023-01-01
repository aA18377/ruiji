package com.fff.pojo;

/**
 * @Auther: zhaoss
 * @Date: 2023/1/1 - 01 - 01 - 21:40
 * @Description: com.fff.pojo
 * @version: 1.0
 */
public class Tjpo {
    private String name;
    private Integer num;

    public Tjpo(String name, Integer num) {
        this.name = name;
        this.num = num;
    }

    public Tjpo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
