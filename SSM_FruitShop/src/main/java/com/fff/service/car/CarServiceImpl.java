package com.fff.service.car;

import com.fff.cons.Const;
import com.fff.mapper.car.CarMapper;
import com.fff.pojo.Car;
import com.fff.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/27 - 12 - 27 - 21:24
 * @Description: com.fff.service.car
 * @version: 1.0
 */
@Service
public class CarServiceImpl extends SqlSessionDaoSupport implements CarService {
    @Autowired
    private CarMapper mapper;
    @Override
    public Integer addCar(Car car) {

        return mapper.addCar(car);
    }

    @Override
    public double itemPrice(long id) {
        return mapper.itemPrice(id);
    }

    @Override
    public List<Car> findAll() {
        return mapper.findAll();
    }

    @Override
    public void deleteById(int id) {
        mapper.deleteById(id);
    }
}
