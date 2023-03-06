package com.yanmou.mybatisplusstudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanmou.mybatisplusstudy.mapper.AddressBookMapper;
import com.yanmou.mybatisplusstudy.pojo.AddressBook;
import com.yanmou.mybatisplusstudy.service.AddressBookSerive;
import org.springframework.stereotype.Service;

/**
 * @Auther: zhaoss
 * @Date: 2023/3/5 - 03 - 05 - 14:32
 * @Description: com.yanmou.mybatisplusstudy.service.impl
 * @version: 1.0
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper,AddressBook>implements AddressBookSerive {
}
