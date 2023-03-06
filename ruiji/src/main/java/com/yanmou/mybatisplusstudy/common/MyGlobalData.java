package com.yanmou.mybatisplusstudy.common;

import com.yanmou.mybatisplusstudy.pojo.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Auther: zhaoss
 * @Date: 2023/2/25 - 02 - 25 - 21:19
 * @Description: com.yanmou.mybatisplusstudy.common
 * @version: 1.0
 */
@ControllerAdvice
@Slf4j
public class MyGlobalData {
    @ModelAttribute(name = "loginUser")
    public Employee map(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employee loginUser = (Employee) session.getAttribute("loginUser");
        return loginUser;
    }
}
