package com.yanmou.mybatisplusstudy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanmou.mybatisplusstudy.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: zhaoss
 * @Date: 2023/2/21 - 02 - 21 - 12:09
 * @Description: com.yanmou.mybatisplusstudy.mapper
 * @version: 1.0
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
