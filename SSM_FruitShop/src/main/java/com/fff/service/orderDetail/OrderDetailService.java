package com.fff.service.orderDetail;

import com.fff.pojo.ItemOrder;
import com.fff.pojo.OrderDetail;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/17 - 12 - 17 - 14:19
 * @Description: com.fff.service.orderDetail
 * @version: 1.0
 */
public interface OrderDetailService {
    /**
     * 万能搜索sql
     * @param sql
     * @return
     */
    List<OrderDetail> selectPageBySql(String sql);

    /**
     * 查询所有
     * @return
     */
    List<OrderDetail> findAll();




    /**
     * 订单详情分页
     * @param inputPage
     * @param pageSize
     * @param id
     * @return
     */
    Map<String,Object> listDetailPage(String inputPage, int pageSize, String id);
}
