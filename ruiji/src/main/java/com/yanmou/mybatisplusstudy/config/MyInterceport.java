package com.yanmou.mybatisplusstudy.config;

import com.yanmou.mybatisplusstudy.pojo.Employee;
import com.yanmou.mybatisplusstudy.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Auther: zhaoss
 * @Date: 2023/2/24 - 02 - 24 - 15:21
 * @Description: com.yanmou.mybatisplusstudy.config
 * @version: 1.0
 */
@Slf4j
public class MyInterceport implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession();
        StringBuffer requestURL = request.getRequestURL();


        Employee loginUser = (Employee) session.getAttribute("loginUser");
        User visitUser = (User) session.getAttribute("visitUser");
        if (loginUser != null || visitUser != null) {
            return true;
        } else if (visitUser == null) {
            response.sendRedirect("/user/loginView");
        } else if (loginUser == null) {
            response.sendRedirect("/employee/toLogin");
        }
        return false;
    }
}
