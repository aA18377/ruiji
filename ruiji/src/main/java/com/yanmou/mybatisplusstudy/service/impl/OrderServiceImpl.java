package com.yanmou.mybatisplusstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanmou.mybatisplusstudy.mapper.OrderMapper;
import com.yanmou.mybatisplusstudy.pojo.Orders;
import com.yanmou.mybatisplusstudy.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @Auther: zhaoss
 * @Date: 2023/3/5 - 03 - 05 - 16:20
 * @Description: com.yanmou.mybatisplusstudy.service.impl
 * @version: 1.0
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {
}
