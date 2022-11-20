package com.yan.servlet;

import com.alibaba.fastjson.JSONObject;
import com.yan.pojo.SmbmsBill;
import com.yan.pojo.SmbmsProvider;
import com.yan.service.BillService;
import com.yan.service.BillServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/10/2 - 10 - 02 - 16:41
 * @Description: com.yan.servlet
 * @version: 1.0
 */
public class BillServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        BillService service = new BillServiceImpl();
        if ("query".equals(method)){
            showBill(service,req,resp);
        }else if ("view".equals(method)){
            billInfo(service,req,resp);
        } else if ("delbill".equals(method)){
            delBill(service,req,resp);
        } else if ("modify".equals(method)){
            modifyView(service,req,resp);
        } else if ("getproviderlist".equals(method)){
            getproviderlist(service,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        BillService service = new BillServiceImpl();
        if ("add".equals(method)){
            addBill(service,req,resp);
        }else if ("modifysave".equals(method)){
            modify(service,req,resp);
        }
    }
    //供应商列表（修改）
    public void getproviderlist(BillService service,HttpServletResponse resp){
        List<SmbmsProvider> proList = service.showPro();
        String s = JSONObject.toJSONString(proList);
        resp.setContentType("application/json");
        try {
            PrintWriter writer = resp.getWriter();
            writer.print(s);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //订单修改
    public void modify(BillService service,HttpServletRequest req,HttpServletResponse resp){
        String billCode = req.getParameter("billCode");
        String productName = req.getParameter("productName");
        String productUnit = req.getParameter("productUnit");
        String productCount = req.getParameter("productCount");
        String totalPrice = req.getParameter("totalPrice");
        String providerId = req.getParameter("providerId");
        String isPayment = req.getParameter("isPayment");
        HttpSession session = req.getSession();
        Long bid = Long.parseLong(session.getAttribute("bid").toString());
        SmbmsBill sb = new SmbmsBill();
        sb.setId(bid);
        session.removeAttribute("bid");
        sb.setBillCode(billCode);
        sb.setProductName(productName);
        sb.setProductUnit(productUnit);
        sb.setProductCount(Double.parseDouble(productCount));
        sb.setTotalPrice(Double.parseDouble(totalPrice));
        sb.setProviderId(Long.parseLong(providerId));
        sb.setIsPayment(Long.parseLong(isPayment));
        service.upBill(sb);
        showBill(service,req,resp);
    }

    //订单修改视图
    public void modifyView(BillService service,HttpServletRequest req,HttpServletResponse resp){
        String billid = req.getParameter("billid");
        HttpSession session = req.getSession();
        session.setAttribute("bid",billid);
        SmbmsBill smbmsBill = service.billInfo(Integer.parseInt(billid));
        req.setAttribute("bill",smbmsBill);
        try {
            req.getRequestDispatcher("billmodify.jsp").forward(req,resp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e){
            e.printStackTrace();
        }
    }

    //订单删除
    public void delBill(BillService service,HttpServletRequest req,HttpServletResponse resp){
        String billid = req.getParameter("billid");
        Map<String,String> map = new HashMap<String, String>(1);
        if (billid !=null){
            int bid = Integer.parseInt(billid);
            if (service.delCheck(bid)){
                int result = service.delBill(bid);
                if (result == 1){
                    map.put("delResult","true");
                }else{
                    map.put("delResult","false");
                }
            }else {
                map.put("delResult","notexist");
            }
        }
        String s = JSONObject.toJSONString(map);
        resp.setContentType("application/json");
        try {
            PrintWriter writer = resp.getWriter();
            writer.print(s);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        showBill(service,req,resp);
    }

    //订单信息
    public void billInfo(BillService service,HttpServletRequest req,HttpServletResponse resp){
        String billid = req.getParameter("billid");
        int bid = 0;
        if (billid != null){
            bid = Integer.parseInt(billid);
        }
        SmbmsBill smbmsBill = service.billInfo(bid);
        req.setAttribute("bill",smbmsBill);
        try {
            req.getRequestDispatcher("billview.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //订单添加
    public void addBill(BillService service,HttpServletRequest req,HttpServletResponse resp){
        String billCode = req.getParameter("billCode");
        String productName = req.getParameter("productName");
        String productUnit = req.getParameter("productUnit");
        String productCount = req.getParameter("productCount");
        String totalPrice = req.getParameter("totalPrice");
        String providerId = req.getParameter("providerId");
        String isPayment = req.getParameter("isPayment");
        SmbmsBill smbmsBill = new SmbmsBill();
        smbmsBill.setBillCode(billCode);
        smbmsBill.setProductName(productName);
        smbmsBill.setProductUnit(productUnit);
        smbmsBill.setIsPayment(Long.parseLong(isPayment));
        smbmsBill.setProviderId(Long.parseLong(providerId));
        smbmsBill.setTotalPrice(Double.parseDouble(totalPrice));
        smbmsBill.setProductCount(Double.parseDouble(productCount));
        service.addBill(smbmsBill);
        try {
            resp.sendRedirect("billlist.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //订单列表
    public void showBill(BillService service,HttpServletRequest req,HttpServletResponse resp){
        //参数
        String queryProductName = req.getParameter("queryProductName");
        String queryProviderId = req.getParameter("queryProviderId");
        String queryIsPayment = req.getParameter("queryIsPayment");
        List<SmbmsBill> smbmsBills = null;
        //默认值
        int iid = 0;
        int qid = 0;
        //非空判断
        if (queryProviderId != null && queryIsPayment != null){
            qid = Integer.parseInt(queryIsPayment);
            iid = Integer.parseInt(queryProviderId);
        }
        if (queryProductName == null){
            queryProductName = "";
        }
        smbmsBills = service.showBillList(queryProductName,iid,qid);

        List<SmbmsProvider> proList = service.showPro();
        req.setAttribute("billList",smbmsBills);
        req.setAttribute("queryProductName",queryProductName);
        req.setAttribute("queryIsPayment",qid);
        req.setAttribute("providerList",proList);
        req.setAttribute("queryProviderId",queryProviderId);
        try {
            req.getRequestDispatcher("billlist.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
