package com.fff.pojo;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/20 - 12 - 20 - 12:45
 * @Description: com.fff.pojo
 * @version: 1.0
 */

public class NavItemCategory {
    private ItemCategory father;
    private List<ItemCategory> childrens;

    public NavItemCategory(ItemCategory father, List<ItemCategory> childrens) {
        this.father = father;
        this.childrens = childrens;
    }

    public NavItemCategory() {
    }

    public ItemCategory getFather() {
        return father;
    }

    public void setFather(ItemCategory father) {
        this.father = father;
    }

    public List<ItemCategory> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<ItemCategory> childrens) {
        this.childrens = childrens;
    }

    @Override
    public String toString() {
        return "NavItemCategory{" +
                "father=" + father +
                ", childrens=" + childrens +
                '}';
    }
}
