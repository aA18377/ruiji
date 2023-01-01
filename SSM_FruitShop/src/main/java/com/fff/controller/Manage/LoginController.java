package com.fff.controller.Manage;

import com.fff.cons.Const;
import com.fff.pojo.Manage;
import com.fff.service.login.MLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/7 - 12 - 07 - 23:09
 * @Description: com.fff.controller
 * @version: 1.0
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private MLoginService service;

    /**
     * 跳转管理员登录视图
     */
    @RequestMapping("/adminLogin")
    public String adminLoginPage() {
        return "login/aLogin";
    }

    /**
     * 登入后台
     *
     * @return
     */
    @RequestMapping("/toLogin")
    public String adminLogin(Manage loginManage, HttpSession session) {
        boolean flag = service.findOneByEntry(loginManage);
        if (flag){
            session.setAttribute(Const.ADMIN_USER,loginManage);
        return "login/aFrame";
        }
        return adminLoginPage();
    }


    /**
     * 注销用户
     * @return
     */
    @RequestMapping("/aLoginOut")
    public String adminLoginOut(HttpSession session){
        session.setAttribute(Const.ADMIN_USER,null);
        return adminLoginPage();
    }


}
