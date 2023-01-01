package com.fff.service.itemOrder;

import com.fff.mapper.itemOrder.ItemOrderMapper;
import com.fff.pojo.Car;
import com.fff.pojo.Item;
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
 * @Date: 2022/12/16 - 12 - 16 - 22:54
 * @Description: com.fff.service.itemOrder
 * @version: 1.0
 */
@Service
public class ItemOrderServiceImpl extends SqlSessionDaoSupport implements ItemOrderService {
    @Autowired
    private ItemOrderMapper mapper;


    @Override
    public List<ItemOrder> selectPageBySql(String sql) {
        return mapper.selectPageBySql(sql);
    }

    @Override
    public List<ItemOrder> findAll() {
        return mapper.findAll();
    }


    @Override
    public Map<String, Object> listPage(String inputPage, int pageSize, String code) {
        String sql = "";
        Map<String, Object> map = null;
        if (code != null) {
            sql = "select * from item_order where code like \"%" + code + "%\"";
            map = PageUtil.PageBySql(inputPage, pageSize, sql, mapper);
        } else {
            sql = "select * from item_order";
            map = PageUtil.PageBySql(inputPage, pageSize, sql, mapper);
        }

        return map;
    }

    @Override
    public Map<String, Object> listDetailPage(String inputPage, int pageSize, String id) {
        String sql = "";
        Map<String, Object> map = null;
        sql = "select * from order_detail where order_id = " + id;
        map = PageUtil.PageBySql(inputPage, pageSize, sql, mapper);
        return map;
    }

    /**
     * 订单操作公共方法
     * 1--已取消
     * 2--去收货
     * 3--已收货
     * @param id
     */
    @Override
    public void doItem(int id,int status) {
        mapper.itemDo(id, status);
    }

    @Override
    public List<ItemOrder> findAllByTerms(Integer status) {
        return mapper.findAllByTerms(status);
    }

    @Override
    public int updateForId(Car item) {
        return mapper.updateForId(item);
    }

    @Override
    public OrderDetail selectDetailById(long id) {
        return mapper.selectDetailById(id);
    }

    @Override
    public void deleteCarById(Car car) {
        mapper.deleteCarById(car);
    }

    @Override
    public Car selectCarById(long id) {
        return mapper.selectCarById(id);
    }

    @Override
    public int addItemOrder(ItemOrder itemOrder) {
        return mapper.addItemOrder(itemOrder);
    }

    @Override
    public int addOrderDetail(OrderDetail detail) {
        return mapper.addOrderDetail(detail);
    }

    @Override
    public Item selectItemGMnum(long id) {
        return mapper.selectItemGMnum(id);
    }


}
