package com.fff.controller.User;

import com.fff.cons.Const;
import com.fff.controller.Manage.ItemController;
import com.fff.pojo.Item;
import com.fff.pojo.Sc;
import com.fff.pojo.User;
import com.fff.service.item.ItemService;
import com.fff.service.sc.scService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/25 - 12 - 25 - 11:33
 * @Description: com.fff.controller.User
 * @version: 1.0
 */
@Controller
@RequestMapping("/sc")
public class ScController {
    @Autowired
    private scService service;
    /**
     * 收藏纪录+1
     * 收藏列表
     * 分别用户各自的收藏
     * 不能多次收藏同个商品
     * @return 返回跳转URL并执行展示收藏列表
     */
    @RequestMapping("/exAdd")
    public String sc(Integer itemId, Model model, @SessionAttribute(Const.LOGIN_USER) User loginUser){
        service.insertByEntry(itemId,loginUser);
        service.scAdd(itemId);
        return scList(model);
    }

    /**
     * 删除收藏
     * @param id
     * @param  loginUser
     * @return
     */
    @RequestMapping("/delete")
    public String delete(int id,Model model,@SessionAttribute(Const.LOGIN_USER) User loginUser){
        service.deleteById(id);
        return sc(0,model,loginUser);
    }

    /**
     * 商品收藏列表
     */
    @RequestMapping("/findBySql")
    public String scList(Model model){
        List<Sc> pagers = service.scList();
        model.addAttribute("pagers",pagers);
        return "/Item/my";
    }
}
