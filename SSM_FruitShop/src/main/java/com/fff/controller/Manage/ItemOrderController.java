package com.fff.controller.Manage;

import com.fff.service.itemOrder.ItemOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/16 - 12 - 16 - 22:54
 * @Description: com.fff.controller
 * @version: 1.0
 */

@Controller
@RequestMapping("/itemOrder")
public class ItemOrderController {
    @Autowired
    private ItemOrderService service;

    /**
     * 跳转到订单管理页面
     * @return
     */
    @RequestMapping("/findBySql")
    public String itemOrderPage(Model model,String inputPage,String code){
        int pageSize = 10;
        Map<String, Object> map = service.listPage(inputPage, pageSize, code);
        model.addAttribute("datas",map);
        if (code != null){
            model.addAttribute("code",code);
        }
        return "/itemOrder/itemOrder";
    }
}
