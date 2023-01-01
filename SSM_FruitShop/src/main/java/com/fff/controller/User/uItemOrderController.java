package com.fff.controller.User;

import com.fff.cons.Const;
import com.fff.pojo.*;
import com.fff.service.itemOrder.ItemOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/26 - 12 - 26 - 22:43
 * @Description: com.fff.controller.User
 * @version: 1.0
 */
@Controller
@RequestMapping("/itemOrder")
public class uItemOrderController {
    @Autowired
    private ItemOrderService service;


    /**
     * 全部订单
     * 已取消订单
     *
     * @param model
     * @return
     */
    @RequestMapping("/my")
    public String my(Model model) {
        List<ItemOrder> all = service.findAllByTerms(null);
        List<ItemOrder> yqx = service.findAllByTerms(1);
        List<ItemOrder> dfh = service.findAllByTerms(2);
        List<ItemOrder> dsh = service.findAllByTerms(3);
        model.addAttribute("all", all);
        model.addAttribute("yqx", yqx);
        model.addAttribute("dfh", dfh);
        model.addAttribute("dsh", dsh);
        return "itemOrder/my";
    }


    /**
     * 跳转订单评价页面
     *
     * @param id 要评价的商品id
     */
    @RequestMapping("/pj")
    public String pj(int id, Model model) {
        model.addAttribute("id", id);
        return "itemOrder/pj";
    }

    /**
     * 取消订单
     *
     * @param id
     */
    @RequestMapping("/qx")
    public String qx(int id) {
        service.doItem(id, 1);
        return "redirect:/itemOrder/my";
    }

    /**
     * 去收货
     */
    @RequestMapping("/sh")
    public String sh(int id) {
        service.doItem(id, 3);
        return "redirect:/itemOrder/my";
    }

    /**
     * 购物车结算
     *这个方法是非事务方法，新增订单详情方法依赖于新增订单方法生成的id，所以不能在一个事务中
     */
    @RequestMapping("/exAdd")
    @ResponseBody
    @Transactional(propagation= Propagation.NOT_SUPPORTED)
    public String exAdd(@RequestBody List<Car> list, @SessionAttribute(Const.LOGIN_USER) User user) {
        ItemOrder itemOrder = new ItemOrder();
        List<OrderDetail> orderDetailList = new ArrayList<>(list.size());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String code = String.valueOf(timestamp.getTime()) + "001";
        for (int i = 0; i < list.size(); i++) {
            //查看car中的itemid
            Car arr = list.get(i);
            Car car = service.selectCarById(arr.getId());
            Item l = service.selectItemGMnum(car.getItemId());
            car.setNum(list.get(i).getNum()+l.getGmNum());
            //通过itemid修改item中的购买次数
            int i1 = service.updateForId(car);


            //订单详情
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setItemId(car.getItemId());
            orderDetail.setTotal(String.valueOf(car.getPrice()));
            orderDetail.setNum(list.get(i).getNum());
            orderDetail.setStatus(0);
            orderDetailList.add(orderDetail);


            //订单总金额
            if (itemOrder.getTotal() == null) {
                itemOrder.setTotal(String.valueOf(car.getPrice()));
            } else {
                itemOrder.setTotal(String.valueOf(Double.parseDouble(itemOrder.getTotal()) + car.getPrice()));
            }
            //结算完成，清除购物车
            service.deleteCarById(car);
        }
        //新增订单
        itemOrder.setIsDelete(0);
        itemOrder.setUserId(user.getId());
        itemOrder.setAddTime(timestamp);
        itemOrder.setCode(code);
        itemOrder.setStatus(0);
        int i2 = service.addItemOrder(itemOrder);


        //新增订单详情
        String sql = "select id as oid from item_order where code = "+code;
        List<ItemOrder> itemOrders = service.selectPageBySql(sql);

        ItemOrder next = itemOrders.get(0);
        System.out.println(next);
        for (int i = 0; i < orderDetailList.size(); i++) {
            orderDetailList.get(i).setOrderId(next.getId());
            service.addOrderDetail(orderDetailList.get(i));
        }

        return "suceesful";

    }





}
