package com.fff.service.sc;

import com.fff.pojo.Item;
import com.fff.pojo.Sc;
import com.fff.pojo.User;
import org.springframework.ui.Model;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/25 - 12 - 25 - 22:16
 * @Description: com.fff.service.sc
 * @version: 1.0
 */
public interface scService {
    /**
     * 收藏列表
     */
    List<Sc> scList();

    /**
     * id删除
     */
    void deleteById(int id);

    /**
     * 收藏数+1
     */
    void scAdd(int id);

    Item findOneByIdForItem(int id);

    /**
     * 新增收藏
     */
    void insertByEntry(int itemId, User user);

    /**
     * 查询收藏是否重复
     */
    boolean repactSc(long userId,int item_id);

    /**
     * 查询单个收藏
     */
    Sc findOneById(int item_id);

}
