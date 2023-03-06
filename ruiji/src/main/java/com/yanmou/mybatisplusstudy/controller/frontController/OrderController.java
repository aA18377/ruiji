package com.yanmou.mybatisplusstudy.controller.frontController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanmou.mybatisplusstudy.common.R;
import com.yanmou.mybatisplusstudy.pojo.*;
import com.yanmou.mybatisplusstudy.service.OrderDetailService;
import com.yanmou.mybatisplusstudy.service.OrderService;
import com.yanmou.mybatisplusstudy.service.impl.AddressBookServiceImpl;
import com.yanmou.mybatisplusstudy.service.impl.ShoppingCartServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2023/3/5 - 03 - 05 - 16:37
 * @Description: com.yanmou.mybatisplusstudy.controller.frontController
 * @version: 1.0
 */
@Controller
@Slf4j
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private ShoppingCartServiceImpl cartService;
    @Autowired
    private AddressBookServiceImpl addressBookService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShoppingCartController cartController;


    /**
     * 下单
     *
     * @return
     */
    @PostMapping("/submit")
    @ResponseBody
    public R<String> submit(@RequestBody Orders orders, @SessionAttribute("visitUser") User user) {
        orders.setUserId(user.getId());
        orders.setOrderTime(new Timestamp(System.currentTimeMillis()));
        QueryWrapper<ShoppingCart> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", user.getId());
        double amount = 0;
        List<ShoppingCart> list = cartService.list(wrapper);
        boolean detailFlag = false;

        for (ShoppingCart shoppingCart : list) {
            amount = shoppingCart.getAmount() + amount;
        }
        orders.setAmount(amount);
        orders.setCheckoutTime(new Timestamp(System.currentTimeMillis()));
        orders.setPhone(user.getPhone());
        orders.setUserName(user.getName());
        AddressBook addressBook = addressBookService.getById(orders.getAddressBookId());
        orders.setConsignee(addressBook.getConsignee());
        orders.setAddress(addressBook.getDetail());
        orders.setStatus(4);
        boolean save = orderService.save(orders);

        log.info("回填的orderid：" + orders.getId());
        //不在上一个foreach解决是为了获取回填的orderid
        for (ShoppingCart shoppingCart : list) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(shoppingCart, orderDetail, "id");
            orderDetail.setOrderId(orders.getId());
            detailFlag = orderDetailService.save(orderDetail);
        }
        //订单号
        UpdateWrapper<Orders> ordersUpdateWrapper = new UpdateWrapper<>();
        ordersUpdateWrapper.setSql("number = id");
        ordersUpdateWrapper.eq("id",orders.getId());
        boolean update = orderService.update(ordersUpdateWrapper);

        if (save && detailFlag ) {
            //清空购物车
            return cartController.clean(user);
        }
        return R.error(null);
    }

    /**
     * 成功支付页
     */
    @GetMapping("/pay-success.html")
    public String paySuccess() {

        return "front/page/pay-success";
    }

    /**
     * 订单列表
     */
    @GetMapping("/userPage")
    @ResponseBody
    public R<IPage<Orders>> userPage(Long page, Long pageSize, @SessionAttribute("visitUser") User user) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",user.getId());
        IPage<Orders> iPage = new Page<>(page,pageSize);
        return R.success(orderService.page(iPage, wrapper));
    }

    /**
     * 分页
     */
    @ResponseBody
    @GetMapping("/page")
    public R<IPage<Orders>> page(Long page,Long pageSize,Long number,Timestamp beginTime,Timestamp endTime){
        log.info("page:"+page+"    pageSize:"+pageSize);
        IPage<Orders> iPage = new Page<>(page,pageSize);
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq(number != null,"number",number);
        wrapper.between(beginTime != null && endTime != null,"order_time",beginTime,endTime);
        return  R.success(orderService.page(iPage, wrapper));
    }

}
