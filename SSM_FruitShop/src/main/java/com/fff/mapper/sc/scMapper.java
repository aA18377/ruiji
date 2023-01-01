package com.fff.mapper.sc;

import com.fff.mapper.MapperDao;
import com.fff.pojo.Item;
import com.fff.pojo.Sc;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/25 - 12 - 25 - 22:14
 * @Description: com.fff.mapper.sc
 * @version: 1.0
 */
@Repository
public interface scMapper extends MapperDao<Sc> {
    void scAdd(Item item);

    Item findOneByIdForItem(int id);

    /**
     * 查询单个商品是否重复
     */
    Sc findItemForRepeat(@Param("userId") long id1,@Param("itemId") int id2);
}
