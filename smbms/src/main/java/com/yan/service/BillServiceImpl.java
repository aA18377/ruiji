package com.yan.service;

import com.yan.dao.BillDao;
import com.yan.pojo.SmbmsBill;
import com.yan.pojo.SmbmsProvider;
import com.yan.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.rmi.MarshalledObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/10/2 - 10 - 02 - 16:38
 * @Description: com.yan.service
 * @version: 1.0
 */
public class BillServiceImpl implements BillService {

    public List<SmbmsBill> showBillList(String billName, int proId, int queryId) {
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        BillDao mapper = sqlsession.getMapper(BillDao.class);
        java.util.Map<String, Object> map = new HashMap<String, Object>(3);
        map.put("queryProductName",billName);
        map.put("queryProviderId", proId);
        map.put("queryIsPayment", queryId);
        List<SmbmsBill> billList = mapper.showBillList(map);
        sqlsession.close();
        return billList;
    }

    public List<SmbmsProvider> showPro() {
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        BillDao mapper = sqlsession.getMapper(BillDao.class);
        List<SmbmsProvider> smbmsProviders = mapper.showProList();
        sqlsession.close();
        return smbmsProviders;
    }

    public void addBill(SmbmsBill bill) {
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        BillDao mapper = sqlsession.getMapper(BillDao.class);
        mapper.addBill(bill);
        sqlsession.commit();
        sqlsession.close();
    }

    public SmbmsBill billInfo(int bid) {
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        BillDao mapper = sqlsession.getMapper(BillDao.class);
        SmbmsBill smbmsBill = mapper.billInfo(bid);
        sqlsession.close();
        return smbmsBill;
    }

    public int delBill(int bid) {
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        BillDao mapper = sqlsession.getMapper(BillDao.class);
        int i = mapper.delBill(bid);
        sqlsession.commit();
        sqlsession.close();
        return i;
    }

    public boolean delCheck(int bid){
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        BillDao mapper = sqlsession.getMapper(BillDao.class);
        boolean flag = false;
        SmbmsBill smbmsBill = mapper.delCheck(bid);
        if (smbmsBill != null){
            flag = true;
        }
        sqlsession.close();
        return flag;
    }

    public void upBill(SmbmsBill bil){
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        BillDao mapper = sqlsession.getMapper(BillDao.class);
        mapper.upBill(bil);
        sqlsession.commit();
        sqlsession.close();
    }
}
