package com.yan.servlet;

import com.yan.constant.Constant;
import com.yan.pojo.SmbmsUser;
import com.yan.service.UserService;
import com.yan.service.UserServiceIml;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Auther: zhaoss
 * @Date: 2022/9/1 - 09 - 01 - 22:56
 * @Description: com.yan.servlet
 * @version: 1.0
 */
public class LoginServlet extends HttpServlet {
    UserService loginService = new UserServiceIml();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userPassword = null;
        SmbmsUser loginUser = null;
        HttpSession session = req.getSession();;
        //获取前端参数
        userPassword = req.getParameter("userPassword");
        loginUser = loginService.getLogin(req.getParameter("userCode"));
        //进行比对
        if (loginUser != null) {
            if (loginUser.getUserPassword().equals(userPassword)) {
                session.setAttribute(Constant.USERSESSION, loginUser); //放入session，过滤未登录用户越权登陆
                resp.sendRedirect("/jsp/frame.jsp");
                return;
            }
        }
        resp.sendRedirect("/login.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
