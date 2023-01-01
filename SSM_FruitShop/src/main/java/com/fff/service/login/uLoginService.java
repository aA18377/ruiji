package com.fff.service.login;

import com.fff.pojo.Item;
import com.fff.pojo.ItemCategory;
import com.fff.pojo.NavItemCategory;
import com.fff.pojo.User;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/18 - 12 - 18 - 21:14
 * @Description: com.fff.service.login
 * @version: 1.0
 */
public interface uLoginService {
    /**
     * 验证登录用户
     * @param user
     * @return
     */
    boolean selectOneByEntry(User user);

    /**
     * 查询登录用户信息
     */
    User selectLoginedUser(User user);

    /**
     * 导航栏类目
     */
    List<NavItemCategory> getNav();

    /**
     * 折扣商品
     */
    List<Item> zkItemList();

    /**
     * 热门商品
     */
    List<Item> fireItemList();

    /**
     * 用户注册
     */
    void addNewUser(User user);
}
