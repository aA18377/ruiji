package com.yanmou.mybatisplusstudy.controller.backendController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanmou.mybatisplusstudy.common.R;
import com.yanmou.mybatisplusstudy.pojo.Employee;
import com.yanmou.mybatisplusstudy.service.impl.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * @Auther: zhaoss
 * @Date: 2023/2/21 - 02 - 21 - 16:51
 * @Description: com.yanmou.mybatisplusstudy.controller
 * @version: 1.0
 */
@Controller
@Slf4j
@RequestMapping("/employee")
@SessionAttributes(names = "employeeToSession")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl service;
    public static final String LOGIN_USER = "loginUser";

    /**
     * 后台登录页
     */
    @GetMapping("/toLogin")
    public String toLogin() {
        return "/backend/page/login/login";
    }

    /**
     * 执行后台登录
     *
     * @param request session域
     * @return 通用结果集
     */
    @ResponseBody
    @PostMapping(value = "/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        R<Employee> result = service.login(employee);
        if (result.getCode() == 1) {
            request.getSession().setAttribute("loginUser", result.getData());

        }
        return result;
    }

    /**
     * 后台系统退出
     */
    @PostMapping("/logout")
    @ResponseBody
    public R logoutInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        //登录用户唯一
        session.removeAttribute("loginUser");
        return R.success(null);
    }

    /**
     * 员工添加页
     */
    @GetMapping("add.html")
    public String add() {
        return "backend/page/member/add";
    }

    /**
     * 执行员工添加
     */
    @ResponseBody
    @PostMapping
    public R addTODO(@RequestBody Employee employee, HttpServletRequest request) {
        Employee loginUser = (Employee) request.getSession().getAttribute("loginUser");
        return service.addTODO(employee, loginUser.getId());
    }

    /**
     * 分页
     */
    @ResponseBody
    @GetMapping("/page")
    public R page(@RequestParam("page") long current, @RequestParam("pageSize") long pageSize, @Nullable @RequestParam("name") String name) {
        Page<Employee> page = new Page<>(current, pageSize);
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        //根据id进行排序
        wrapper.orderByDesc("id");
        //当name不为空时进行模糊查询
        wrapper.like(name != null, "name", name);
        return R.success(service.page(page, wrapper));
    }

    /**
     * 分页完成后的返回返回上一页
     */
    @GetMapping("/list.html")
    public String goBackPage() {
        return "backend/page/member/list";
    }

    /**
     * 禁用账户-修改用户信息
     */
    @PutMapping
    @ResponseBody
    public R<String> update(@RequestBody Employee employee) {
            return service.updateInfo(employee);
    }

    /**
     * 员工修改页
     */
    @ResponseBody
    @GetMapping("/{id}")
    public R<Employee> update(@PathVariable("id") long id){
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        Employee dbUser = service.getOne(wrapper);
        if (dbUser != null){
            return R.success(dbUser);
        }
        return R.error(null);
    }
}
