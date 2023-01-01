package com.fff.service.itemCategory;

import com.fff.pojo.Item;
import com.fff.pojo.ItemCategory;
import com.fff.pojo.Manage;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/8 - 12 - 08 - 18:22
 * @Description: com.fff.service
 * @version: 1.0
 */
public interface itemCategoryService {
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
    List<ItemCategory> findListByPage(Integer start, Integer end);

    /**
     * 查询条目总数
     * @return
     */
    Integer findListCount();

    /**
     * 生成limi索引
     * @param input  跳转页面
     * @param pageSize  最大页面容量
     * @return   分页结果
     */
    Map<String,Object> listPage(String input, int pageSize);

    /**
     * 新增单个一级类目
     * @param itemCategory
     */
    void additemCategoryOne(ItemCategory itemCategory);

    /**
     * 通过id删除一个一级类目
     * @param id
     */
    void deleteItemById(int id);

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
    ItemCategory findOneById(int id);


    /**
     * ID查看二级类目
     * 分页
     * @param id
     * @return
     */
    List<ItemCategory> findItem2(@Param("id") int id,@Param("start") Integer start,@Param("end") Integer end);

    /**
     * 二级类目列表
     * @param id
     * @return
     */
    List<ItemCategory> listItem2(@Param("id") int id);

    /**
     * 二级类目条数
     * @return
     */
    int listItem2Count(int id);

    /**
     * 二级类目分页结果视图数据
     * @param id
     * @param input
     * @param pageSize
     * @return
     */
    Map<String,Object> pageItem2(int id,String input, int pageSize);

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
     * 万能查询sql
     */
    List<ItemCategory> superSelect(String sql);

    /**
     * 万能查询item
     */
    List<Item> selectItem(String sql);
}
