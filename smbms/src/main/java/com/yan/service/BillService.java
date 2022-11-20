package com.yan.service;

import com.yan.pojo.SmbmsBill;
import com.yan.pojo.SmbmsProvider;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/10/2 - 10 - 02 - 16:38
 * @Description: com.yan.service
 * @version: 1.0
 */
public interface BillService {
    //查询订单
    public List<SmbmsBill> showBillList(String billName, int proid, int queryId);
    //查询供应商
    public List<SmbmsProvider> showPro();
    //订单添加
    public void addBill(SmbmsBill bill);
    //订单信息
    public SmbmsBill billInfo(int bid);
    //订单删除
    public int delBill(int bid);
    //订单删除检查
    public boolean delCheck(int bid);
    //订单修改
    public void upBill(SmbmsBill bill);
}
