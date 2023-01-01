package com.fff.controller.User;

import com.fff.pojo.Item;
import com.fff.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/19 - 12 - 19 - 22:47
 * @Description: com.fff.controller.User
 * @version: 1.0
 */
@Controller
@RequestMapping("/item")
public class uItemController {
    @Autowired
    private ItemService service;

    /**
     * 首页导航栏分类商品链接
     * 如果不在前端做标记，可以在数据库中做标记，实现升降序的切换
     * @param  condition 关键词搜索
     */

    @RequestMapping("/shoplist")
    public String shopList(Model model, Item item, String inputPage,String condition) {
        //搜索出商品信息，方便排序时调用
        String sql = "select * from item where category_id_two = "+item.getCategoryIdTwo();
        List<Item> obj_tmp = service.selectPageBySql(sql);
        Iterator<Item> iterator = obj_tmp.iterator();
        Item obj = iterator.next();

        //返回前段的商品列表
        int pagesize = 10;
        model.addAttribute("obj", obj);
        //价格排序
        if (item.getPrice()!=null){
            sql+=" order by price";
        }
        //销量排序
        else if (item.getGmNum() > 0){
            sql+=" order by scNum";
        }
        Map<String, Object> datas = service.listPage(inputPage, pagesize, sql);
        model.addAttribute("datas", datas);

        return "/Item/shoplist";
    }

    /**
     * 商品详情页
     */
    @RequestMapping("/view")
    public String itemView(int id, Model model) {
        Item obj = service.findOneById(id);
        model.addAttribute("obj", obj);
        return "/Item/view";
    }

}
