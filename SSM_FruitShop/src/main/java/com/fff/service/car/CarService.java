package com.fff.service.car;

import com.fff.pojo.Car;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/27 - 12 - 27 - 21:21
 * @Description: com.fff.service.car
 * @version: 1.0
 */

public interface CarService {

    /**
     * 添加到购物车
     * @return
     */
    Integer addCar(Car car);

    /**
     * 查看商品单价
     */
    double itemPrice(long id);

    /**
     * 查询购物车
     */
    List<Car> findAll();

    /**
     * 购物车单项删除
     */
    void deleteById(int id);
}

