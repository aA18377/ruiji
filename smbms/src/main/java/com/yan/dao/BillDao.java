package com.yan.dao;

import com.yan.pojo.SmbmsBill;
import com.yan.pojo.SmbmsProvider;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/10/1 - 10 - 01 - 22:02
 * @Description: com.yan.dao
 * @version: 1.0
 */
public interface BillDao {
   //订单列表&&订单查看
    public List<SmbmsBill> showBillList(Map<String,Object> map);
    //供应商列表
    public List<SmbmsProvider> showProList();
    //订单添加
    public void addBill(SmbmsBill bill);
    //订单信息
    public SmbmsBill billInfo(@Param("bid") int bid);
    //订单删除
    public int delBill(@Param("bid") int bid);
   //订单删除检测
   public SmbmsBill delCheck(@Param("bid") int bid);
   //订单修改
    public void upBill(SmbmsBill bill);

}