package com.fff.mapper.login;

import com.fff.mapper.MapperDao;
import com.fff.pojo.Item;
import com.fff.pojo.ItemCategory;
import com.fff.pojo.NavItemCategory;
import com.fff.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/18 - 12 - 18 - 20:58
 * @Description: com.fff.mapper.login
 * @version: 1.0
 */
@Repository
public interface uLoginMapper{
    /**
     * 查询导航栏一二级类目
     */
    List<NavItemCategory> navList();

    /**
     * 实例查询登录用户信息
     */
    User selectOneByEntry(User user);

    /**
     * 折扣商品
     */
    List<Item> zkItemList();

    /**
     * 热门商品
     */
    List<Item> fireItemList();

    /**
     * 添加新用户
     */
    void addNewUser(User user);
}
