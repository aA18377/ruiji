package com.fff.controller.Manage;

import com.fff.service.orderDetail.OrderDetailService;
import com.fff.service.orderDetail.OrderDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/17 - 12 - 17 - 14:12
 * @Description: com.fff.controller
 * @version: 1.0
 */
@Controller
@RequestMapping("/orderDetail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService service;

    @RequestMapping("/ulist")
    public String userList(Model model,String orderId,String inputPage){
        int pageSize = 10;
        Map<String, Object> map = service.listDetailPage(inputPage, pageSize, orderId);
        model.addAttribute("datas",map);
        return "orderDetail/ulist";
    }
}
