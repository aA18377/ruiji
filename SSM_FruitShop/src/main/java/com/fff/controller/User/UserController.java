package com.fff.controller.User;

import com.fff.cons.Const;
import com.fff.pojo.User;
import com.fff.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/10 - 12 - 10 - 22:57
 * @Description: com.fff.controller
 * @version: 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    /**
     * 分页展示
     *
     * @param model
     * @param inputPage
     * @param userName  搜索参数
     * @return
     */
    @RequestMapping("/findBySql")
    public String userList(Model model, String inputPage, String userName) {
        //页面容量
        int pageSize = 7;
        Map<String, Object> datas = null;
        if (userName == null) {
            datas = service.realPage(inputPage, pageSize);
        } else {
            datas = service.findUserPage(inputPage, pageSize, userName);
        }
        model.addAttribute("datas", datas);
        return "user/user";
    }

    /**
     * 跳转用户信息页面
     */
    @RequestMapping("/view")
    public String userView(Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute(Const.LOGIN_USER);
        User obj = service.findOneUserById(Integer.parseInt(Long.toString(loginUser.getId())));
        model.addAttribute("obj", obj);
        return "/user/view";

    }

    /**
     * 修改用户个人信息
     */
    @RequestMapping("/exUpdate")
    public String exUpdate(Model model, User user, HttpSession session) {
        User loginUser = (User) session.getAttribute(Const.LOGIN_USER);
        user.setId(loginUser.getId());
        service.exUpdate(user);
        return "redirect:/user/view";
    }

}
