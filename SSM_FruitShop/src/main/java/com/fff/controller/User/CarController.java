package com.fff.controller.User;

import com.fff.cons.Const;
import com.fff.pojo.Car;
import com.fff.pojo.User;
import com.fff.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/27 - 12 - 27 - 21:26
 * @Description: com.fff.controller.User
 * @version: 1.0
 */
@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService service;

    /**
     * 添加到购物车
     */
    @ResponseBody
    @RequestMapping("/exAdd")
    public String exAdd(Car car, @SessionAttribute(Const.LOGIN_USER)User loginUser){
        double i = service.itemPrice(car.getItemId());
        car.setUserId(loginUser.getId());
        car.setPrice(i);
        return service.addCar(car).toString();
    }

    /**
     * 查看购物车
     */
    @RequestMapping("/findBySql")
    public String carPage(Model model){
        List<Car> list = service.findAll();
        model.addAttribute("list",list);
        return "car";
    }

    /**
     * 购物车单项/批量删除
     * 批量删除只是发了多个单项删除请求
     */
    @ResponseBody
    @RequestMapping("/delete")
    public String deleteOneById(int id){
        service.deleteById(id);
        return "";
    }


}
