package com.fff.service.orderDetail;

import com.fff.mapper.orderDetail.OrderDetailMapper;
import com.fff.pojo.ItemOrder;
import com.fff.pojo.OrderDetail;
import com.fff.utils.PageUtil;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/17 - 12 - 17 - 14:19
 * @Description: com.fff.service.orderDetail
 * @version: 1.0
 */
@Service
public class OrderDetailServiceImpl extends SqlSessionDaoSupport implements OrderDetailService {
    @Autowired
    private OrderDetailMapper mapper;

    @Override
    public List<OrderDetail> selectPageBySql(String sql) {
        return mapper.selectPageBySql(sql);
    }

    @Override
    public List<OrderDetail> findAll() {
        return mapper.findAll();
    }

    @Override
    public Map<String, Object> listDetailPage(String inputPage, int pageSize, String id) {
        String sql = "";
        Map<String, Object> map = null;
        sql = "select * from order_detail where order_id = " + id;
        map = PageUtil.PageBySql(inputPage, pageSize, sql, mapper);
        return map;
    }

}
