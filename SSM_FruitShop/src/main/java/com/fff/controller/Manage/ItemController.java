package com.fff.controller.Manage;

import com.fff.pojo.ItemCategory;
import com.fff.pojo.Item;
import com.fff.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/11 - 12 - 11 - 17:48
 * @Description: com.fff.controller
 * @version: 1.0
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService service;

    /**
     * 商品列表
     * @param model
     * @param inputPage
     * @return
     */
    @RequestMapping("/findBySql")
    public String selectPageBySql(Model model, String inputPage,String name) {
        int pageSize = 6;
        Map<String, Object> datas = null;
        String sql = "";
        //搜索功能分页
        if (name != null){
            /**
             * 在mapper中用的是$，$没有预编译因此没有加引号
             */
            sql  = "select * from item where name like \"%"+name+"%\"";
            model.addAttribute("name",name);
        }
        //常规分页
        else{
            sql = "select * from item where isDelete = 0";
        }
        datas = service.listPage(inputPage, pageSize,sql);
        model.addAttribute("datas", datas);

        return "/Item/item";
    }

    /**
     * 跳转添加商品页面
     * @return
     */
    @RequestMapping("/add")
    public String addPage(Model model){
        List<ItemCategory> types = service.selectTypes();
        model.addAttribute("types",types);
        return "/Item/add";
    }


    /**
     * 添加商品
     * @return
     */
    @RequestMapping("/exAdd")
    public String addItem(Item item, @RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest req){
        Item uploadItem = service.uploadItemImg(item, files, req);
        service.insertByEntry(uploadItem);
        return "redirect:/item/findBySql";
    }

    /**
     * 下架商品
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String deleteItem(int id){
        service.deleteById(id);
        return "redirect:/item/findBySql";
    }


    /**
     * 跳转到商品修改页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/update")
    public String updatePage(Model model,int id){
        Item obj = service.findOneById(id);
        List<ItemCategory> types = service.selectTypes();
        model.addAttribute("types",types);
        model.addAttribute("obj",obj);
        return "/Item/update";
    }

    /**
     * 执行商品修改
     * @param item
     * @param files
     * @param req
     * @return
     */
    @RequestMapping("/exUpdate")
    public String updateItem(Item item,@RequestParam("file") CommonsMultipartFile[] files,HttpServletRequest req){
        service.updateByEntry(item,files,req);
        return "redirect:/item/findBySql";
    }


}
