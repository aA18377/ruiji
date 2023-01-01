package com.fff.mapper.itemCategory;

import com.fff.pojo.Item;
import com.fff.pojo.ItemCategory;
import com.fff.pojo.Manage;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/8 - 12 - 08 - 16:53
 * @Description: com.fff.mapper
 * @version: 1.0
 */
@Repository
public interface itemCategoryMapper {
    /**
     * 类目列表
     * @return
     */
    List<ItemCategory> findListAll();

    /**
     * 分页
     * @param start
     * @param end
     * @return
     */
    List<ItemCategory> findListByPage(@Param("start") Integer start,@Param("end") Integer end);

    /**
     * 新增单个一级类目
     * @param itemCategory
     */
    void additemCategoryOne(ItemCategory itemCategory);

    /**
     * 通过id删除一个一级类目
     * @param id
     */
    void deleteItemById(@Param("id") int id);

    /**
     * 通过id修改一级类目
     * @param itemCategory
     */
    void updateItemId(ItemCategory itemCategory);

    /**
     * ID查询单类目
     * @param id
     * @return
     */
    ItemCategory findOneById(@Param("id") int id);

    /**
     * ID查看二级类目
     * 分页
     * @param pid
     * @return
     */
    List<ItemCategory> findItem2(@Param("id") int pid,@Param("start") Integer start,@Param("end") Integer end);

    /**
     * 二级类目列表
     * @param pid
     * @return
     */
    List<ItemCategory> listItem2(@Param("id") int pid);


    /**
     * 新增二级类目
     * @param itemCategory
     */
    void addItem2(ItemCategory itemCategory);

    /**
     * 修改二级类目
     * @param itemCategory
     */
    void updateItem2(ItemCategory itemCategory);

    /**
     * 删除二级类目
     * 假删除
     * @param id 二级类目id
     */
    void deleteItem2(@Param("id") int id);

    /**
     * 万能查询
     * @param sql
     * @return
     */
    List<ItemCategory> superSelect(@Param("sql") String sql);

    /**
     * 万能查询item
     */
    List<Item> selectItem(@Param("sql") String sql);

}
