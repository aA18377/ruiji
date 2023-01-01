package com.fff.mapper.itemOrder;

import com.fff.mapper.MapperDao;
import com.fff.pojo.Car;
import com.fff.pojo.Item;
import com.fff.pojo.ItemOrder;
import com.fff.pojo.OrderDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/16 - 12 - 16 - 22:51
 * @Description: com.fff.mapper.itemOrder
 * @version: 1.0
 */
@Repository
public interface ItemOrderMapper extends MapperDao<ItemOrder> {


    /**
     * 订单状态更改
     */
    void itemDo(@Param("id") int id, @Param("status") int status);

    /**
     * 根据状态展示订单列表
     */
    List<ItemOrder> findAllByTerms(@Param("itemStatus")Integer staus);

    /**
     * 更新状态
     */
    int updateForId(Car item);

    /**
     * id查询订单详情
     */
    OrderDetail selectDetailById(@Param("id") long id);

    /**
     * itemid删除购物车
     */
    void deleteCarById(Car car);

    /**
     * 查看购物车
     */
    Car selectCarById(long id);

    /**
     * 新增订单
     */
    int addItemOrder(ItemOrder order);

    /**
     * 新增订单详情
     */
    int addOrderDetail(OrderDetail detail);

    /**
     * 查询订单购买次数
     * @param id
     * @return
     */
    Item selectItemGMnum(@Param("id") long id);

}
