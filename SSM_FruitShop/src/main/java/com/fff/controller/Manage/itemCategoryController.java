package com.fff.controller.Manage;

import com.fff.pojo.Item;
import com.fff.pojo.ItemCategory;
import com.fff.pojo.Tjpo;
import com.fff.service.itemCategory.itemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/8 - 12 - 08 - 16:50
 * @Description: com.fff.controller
 * @version: 1.0
 */
@Controller
@RequestMapping("/itemCategory")
public class itemCategoryController {

    @Autowired
    private itemCategoryService service;

//    @RequestMapping("/findBySql")
    //使用这个方法jsp需要更改一下参数
//    public String list(Model model,String inputPage) {
//        //总记录条数
//        Integer totalCount = service.findListCount();
//        //页面容量
//        Integer pageSize = 8;
//        //总页数
//        Integer totalPageCount = totalCount / pageSize + 1;
//        //真实跳转页数
//        Integer index = PageUtil.inputPage(inputPage, totalPageCount);
//        //用户列表
//        List<ItemCategory> list = service.listPage(index, pageSize);
//        //当前页面
//        int currentPageNo = 1;
//        if (index != null){
//            currentPageNo = index;
//        }
//        model.addAttribute("datas",list);
//        model.addAttribute("totalPageCount",totalPageCount);
//        model.addAttribute("totalCount",totalCount);
//        model.addAttribute("currentPageNo",currentPageNo);
//        model.addAttribute("totalPageCount",totalPageCount);
//        return "ItemCategory";
//    }


    /**
     * 分页
     * @param model
     * @param inputPage 跳转页数
     * @return
     */
    @RequestMapping("/findBySql")
    public String list(Model model,String inputPage) {
        //页面容量
        int pageSize = 10;
        Map<String, Object> datas = service.listPage(inputPage, pageSize);
        model.addAttribute("datas",datas);
        return "ItemCategory/ItemCategory";
    }


    /**
     * 跳转新增类目页面
     * @return
     */
    @RequestMapping("/add")
    public String addPage(){
        return "ItemCategory/itemCategoryAdd1";
    }

    /**
     * 新增单个一级类目
     * @param itemCategory
     * @param model
     * @return
     */
    @RequestMapping("/exAdd")
    public String addItem(ItemCategory itemCategory,Model model){
        service.additemCategoryOne(itemCategory);
        return list(model,"1");
    }

    /**
     * 通过id删除类目
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String deleteItemById(int id){
        service.deleteItemById(id);
        return "forward:/itemCategory/findBySql";
    }

    /**
     * 跳转类目修改页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/update")
    public String updatePage(Model model,int id){
        ItemCategory obj = service.findOneById(id);
        model.addAttribute("obj",obj);
        return "ItemCategory/update";
    }

    /**
     * 修改一级类目
     * @param itemCategory
     * @return
     */
    @RequestMapping("/exUpdate")
    public String updateItem(ItemCategory itemCategory){
        service.updateItemId(itemCategory);
        return "redirect:/itemCategory/findBySql";
    }

    /**
     * 查看二级分类
     * @param model
     * @param pid
     * @return
     */
    @RequestMapping("/findBySql2")
    public String showItem2(Model model,String inputPage,int pid){
        //页面容量
        int pageSize = 7;
        Map<String, Object> datas = service.pageItem2(pid,inputPage, pageSize);
        model.addAttribute("datas",datas);
        model.addAttribute("pid",pid);
        return "ItemCategory/itemCategory2";
    }

    /**
     * 二级类目新增页面
     * @param model
     * @return
     */
    @RequestMapping("/add2")
    public String addItem2Page(Model model,int id){
        model.addAttribute("pid",id);
        return "ItemCategory/add2";
    }

    /**
     * 新增二级分类
     * @param itemCategory
     * @return
     */
    @RequestMapping("/exAdd2")
    public String addItem2(Model model,ItemCategory itemCategory){
        service.addItem2(itemCategory);
        return showItem2(model,"1",Integer.parseInt(Long.toString(itemCategory.getPid())));
    }

    /**
     * 二级类目修改页面
     * @param model
     * @param id 二级类目id
     * @return
     */
    @RequestMapping("/update2")
    public String update2Page(Model model,int id){
        ItemCategory oneById = service.findOneById(id);
        model.addAttribute("obj",oneById);
        return "ItemCategory/update2";
    }

    /**
     * 修改二级类目
     * @return
     */
    @RequestMapping("/exUpdate2")
    public String updateItem2(Model model,ItemCategory itemCategory){
        service.updateItem2(itemCategory);
        return showItem2(model,"1",Integer.parseInt(Long.toString(itemCategory.getPid())));
    }

    /**
     * 删除二级类目
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/delete2")
    public String deleteItem2(Model model,int id){
        ItemCategory oneById = service.findOneById(id);
        service.deleteItem2(id);
        return showItem2(model,"1",Integer.parseInt(Long.toString(oneById.getPid())));
    }

    /**
     * 统计图
     */
    @RequestMapping("/tj")
    public String tj(Model model){
        String sql  = "select * from item_category where pid is null and isDelete = 0 order by id DESC";
        List<ItemCategory> list = service.superSelect(sql);
        List<Tjpo> TjList = new ArrayList<>();
        List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
        for (ItemCategory c:list){
            Tjpo tjpo = new Tjpo();
            int num = 0;
            String sql2 = "SELECT * FROM item WHERE 1=1 and isDelete =0 and category_id_one="+c.getId();
            List<Item> items = service.selectItem(sql2);
            for (Item i : items) {
                num+=i.getGmNum();
            }
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("name", c.getName());
            map.put("value", num);
            maps.add(map);
        }
        model.addAttribute("maps", maps);
        return "/ItemCategory/tj";
    }


}
