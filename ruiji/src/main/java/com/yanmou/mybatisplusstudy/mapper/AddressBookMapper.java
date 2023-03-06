package com.yanmou.mybatisplusstudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanmou.mybatisplusstudy.pojo.AddressBook;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: zhaoss
 * @Date: 2023/3/5 - 03 - 05 - 14:31
 * @Description: com.yanmou.mybatisplusstudy.mapper
 * @version: 1.0
 */
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
