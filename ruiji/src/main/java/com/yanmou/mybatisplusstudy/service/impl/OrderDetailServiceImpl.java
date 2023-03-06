package com.yanmou.mybatisplusstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanmou.mybatisplusstudy.mapper.OrderDetailMapper;
import com.yanmou.mybatisplusstudy.pojo.OrderDetail;
import com.yanmou.mybatisplusstudy.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * @Auther: zhaoss
 * @Date: 2023/3/5 - 03 - 05 - 17:09
 * @Description: com.yanmou.mybatisplusstudy.service.impl
 * @version: 1.0
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
