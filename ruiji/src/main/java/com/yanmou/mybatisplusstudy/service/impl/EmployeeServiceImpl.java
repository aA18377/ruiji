package com.yanmou.mybatisplusstudy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanmou.mybatisplusstudy.common.MyThreadLocal;
import com.yanmou.mybatisplusstudy.common.R;
import com.yanmou.mybatisplusstudy.mapper.EmployeeMapper;
import com.yanmou.mybatisplusstudy.pojo.Employee;
import com.yanmou.mybatisplusstudy.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.Line;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @Auther: zhaoss
 * @Date: 2023/2/21 - 02 - 21 - 12:10
 * @Description: com.yanmou.mybatisplusstudy.service.impl
 * @version: 1.0
 */
@Slf4j
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Autowired
    private EmployeeMapper mapper;

    /**
     * 后台登录
     * @param employee
     * @return
     */
    public R<Employee> login(Employee employee) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        String DBpassword = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());
        wrapper.eq("username", employee.getUsername());
        Employee DBEmployee = mapper.selectOne(wrapper);
        if (DBEmployee == null) {
            return R.error("用户不存在");
        }
        else if (!DBEmployee.getPassword().equals(DBpassword)) {
            return R.error("密码不正确");
        }
        else if (DBEmployee.getStatus() != 1) {
            return R.error("账户已被禁用");
        }
        return R.success(DBEmployee);
    }

    /**
     * 员工添加
     */
    public R addTODO(Employee employee,long id) {
        //设置初始信息
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setStatus(1);
        //employee.setCreateTime(new Timestamp(System.currentTimeMillis()));
       // employee.setUpdateTime(new Timestamp(System.currentTimeMillis()));
       // employee.setUpdateUser(id);
        //employee.setCreateUser(id);
        //该项目雪花算法无法正常生效，于是手动生成学生算法并写法
        IdentifierGenerator identifierGenerator=new DefaultIdentifierGenerator();
        long autoId = identifierGenerator.nextId(employee).longValue();
        employee.setId(autoId);
        int insert = mapper.insert(employee);
        if (insert != 0) {
            return R.success(null);
        }
        return R.error(null);
    }

    /**
     * 更新信息
     */
    public R<String> updateInfo(Employee employee){
        UpdateWrapper<Employee> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",employee.getId());
        wrapper.set(employee.getUsername()!=null,"username",employee.getUsername());
        wrapper.set(employee.getName()!=null,"name",employee.getName());
        wrapper.set(employee.getPhone()!=null,"phone",employee.getPhone());
        wrapper.set(employee.getIdNumber()!=null,"id_number",employee.getIdNumber());
        wrapper.set(employee.getSex()!=null,"sex",employee.getSex());
        wrapper.set("status",employee.getStatus());
       // wrapper.set("update_time", LocalDateTime.now());
       // wrapper.set("update_user",employee.getId());
        int update = mapper.update(employee, wrapper);
        if (update > 0){
            return R.success(null);
        }
        return R.error(null);
    }
}
