package com.fff.mapper.car;

import com.fff.mapper.MapperDao;
import com.fff.pojo.Car;
import org.apache.ibatis.annotations.Param;

import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/27 - 12 - 27 - 21:16
 * @Description: com.fff.mapper.car
 * @version: 1.0
 */
public interface CarMapper extends MapperDao<Car> {

    /**
     * 添加购物车
     */
    Integer addCar(Car car);

    /**
     * 查看商品单价
     */
    double itemPrice(@Param("itemId")long id);
}
