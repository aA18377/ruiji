package com.fff.mapper.item;

import com.fff.mapper.MapperDao;
import com.fff.pojo.Item;
import com.fff.pojo.ItemCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/11 - 12 - 11 - 17:25
 * @Description: com.fff.mapper
 * @version: 1.0
 */
@Repository
public interface ItemMapper extends MapperDao<Item> {
    /**
     * 返回类别
     * @return
     */
    List<ItemCategory> selectTypes();

    /**
     * 通过二级类目id查找单个
     */
    Item selectOneByICID(@Param("id") int id);



}
