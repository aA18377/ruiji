package com.fff.service.itemOrder;

import com.fff.pojo.Car;
import com.fff.pojo.Item;
import com.fff.pojo.ItemOrder;
import com.fff.pojo.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/16 - 12 - 16 - 22:54
 * @Description: com.fff.service.itemOrder
 * @version: 1.0
 */
public interface ItemOrderService {


    /**
     * 万能搜索sql
     * @param sql
     * @return
     */
    List<ItemOrder> selectPageBySql(String sql);

    /**
     * 查询所有
     * @return
     */
    List<ItemOrder> findAll();



    /**
     * 分页By工具类
     */
    Map<String,Object> listPage(String inputPage, int pageSize, String code);

    /**
     * 订单详情分页
     * @param inputPage
     * @param pageSize
     * @param id
     * @return
     */
    Map<String,Object> listDetailPage(String inputPage, int pageSize, String id);

    /**
     * 订单操作
     */
    void doItem(int id,int status);

    /**
     * 根据条件展示订单列表
     */
    List<ItemOrder> findAllByTerms(Integer status);

    /**
     * 实例更新
     */
    int updateForId(Car item);

    /**
     * id查询详情
     */
    OrderDetail selectDetailById(long id);

    /**
     * 购物车结算后清除内容
     */
    void deleteCarById(Car car);

    /**
     * 查看购物车
     */
    Car selectCarById(long id);

    /**
     * 新增订单
     */
    int addItemOrder(ItemOrder itemOrder);

    /**
     * 新增订单详情
     */
    int addOrderDetail(OrderDetail detail);

    /**
     * 查询订单购买次数
     * @param id
     * @return
     */
    Item selectItemGMnum(long id);
}
