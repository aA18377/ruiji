package com.yanmou.mybatisplusstudy.controller.frontController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yanmou.mybatisplusstudy.common.R;
import com.yanmou.mybatisplusstudy.pojo.ShoppingCart;
import com.yanmou.mybatisplusstudy.pojo.User;
import com.yanmou.mybatisplusstudy.service.impl.ShoppingCartServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2023/3/4 - 03 - 04 - 16:45
 * @Description: com.yanmou.mybatisplusstudy.controller.frontController
 * @version: 1.0
 */
@Controller
@Slf4j
@RequestMapping("/shoppingCart")
@SessionAttributes("key")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartServiceImpl service;

    /**
     * 购物车
     *
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public R<List<ShoppingCart>> list(@SessionAttribute("visitUser") User visitUser) {
        QueryWrapper<ShoppingCart> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", visitUser.getId());
        List<ShoppingCart> list = service.list(wrapper);
        return R.success(list);
    }

    /**
     * 添加至购物车
     */
    @PostMapping("/add")
    @ResponseBody
    public R<ShoppingCart> add(@RequestBody ShoppingCart cart, @SessionAttribute("visitUser") User visitUser) {

        //检测二次添加
        UpdateWrapper<ShoppingCart> wrapper = new UpdateWrapper<>();
        wrapper.eq("name", cart.getName());
        wrapper.eq("user_id", visitUser.getId());
        wrapper.setSql("number = number+1");
        boolean update = service.update(wrapper);
        if (update) {
            return R.success(null);
        }
        //一次添加
        cart.setUserId(visitUser.getId());
        cart.setNumber(1);
        boolean save = service.save(cart);
        if (save) {
            return R.success(null);
        }
        return R.error(null);
    }

    /**
     * 清空购物车
     */
    @DeleteMapping("/clean")
    @ResponseBody
    public R<String> clean(@SessionAttribute("visitUser") User visitUser) {
        QueryWrapper<ShoppingCart> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", visitUser.getId());
        boolean remove = service.remove(wrapper);
        if (remove) {
            return R.success(null);
        }
        return R.error(null);
    }

    /**
     * 结算界面
     * @return
     */
   @GetMapping("/add-order.html")
    public String orderView(){
        return "front/page/add-order";
   }

    /**
     * 减少数量
     */
    @PostMapping("sub")
    @ResponseBody
    public R<String> sub(@RequestBody ShoppingCart cart,@SessionAttribute("visitUser") User user){
        UpdateWrapper<ShoppingCart> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_id",user.getId());
        wrapper.eq(cart.getSetmealId() > 0,"setmeal_id",cart.getSetmealId());
        wrapper.eq(cart.getDishId() > 0,"dish_id",cart.getDishId());
        wrapper.eq("number",1);
        boolean remove = service.remove(wrapper);
        if (remove){
            return R.success(null);
        }

        wrapper.clear();
        wrapper.eq("user_id",user.getId());
        wrapper.eq(cart.getSetmealId() > 0,"setmeal_id",cart.getSetmealId());
        wrapper.eq(cart.getDishId() > 0,"dish_id",cart.getDishId());
        wrapper.setSql("number = number-1");
        boolean update = service.update(wrapper);
        if (update){
            return R.success(null);
        }
        return R.error(null);

    }


}
