package com.fff.controller.User;

import com.fff.cons.Const;
import com.fff.pojo.Item;
import com.fff.pojo.NavItemCategory;
import com.fff.pojo.User;
import com.fff.service.login.uLoginService;
import com.fff.service.user.UserService;
import com.fff.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/18 - 12 - 18 - 18:14
 * @Description: com.fff.controller.User
 * @version: 1.0
 */
@Controller
@RequestMapping("/login")
public class uLoginController {
    @Autowired
    private uLoginService service;

    @Autowired
    private UserService userService;

    /**
     * 跳转登录视图
     */
    @RequestMapping("/loginPage")
    public String loginPage() {

        return "login/uLogin";
    }

    /**
     * 跳转首页
     * 导航栏
     * 折扣商品
     * 热门商品--收藏数大于10次，购买数大于五次
     */
    @RequestMapping("/uIndex")
    public String uIndexPage(Model model, HttpSession session) {
        List<NavItemCategory> lbs = service.getNav();
        List<Item> items = service.zkItemList();
        List<Item> items1 = service.fireItemList();
        model.addAttribute("zks", items);
        model.addAttribute("lbs", lbs);
        model.addAttribute("rxs", items1);
        LoginUtil.loginCheck(model, session);
        return "uIndex";
    }


    /**
     * 验证用户登录
     * 存入成功登录用户信息
     */
    @RequestMapping("/utoLogin")
    public String login(User user, HttpSession session, HttpServletRequest req) {
        boolean flag = service.selectOneByEntry(user);
        if (!flag) {
            return loginPage();
        }
        User loginedUser = service.selectLoginedUser(user);
        //加入session
        session.setAttribute(Const.LOGIN_USER, loginedUser);
        return "redirect:/login/uIndex";
    }

    /**
     * 修改密码页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/pass")
    public String pass(Model model, HttpSession session) {
        User user = (User) session.getAttribute(Const.LOGIN_USER);
        model.addAttribute("obj", user);
        return "/login/pass";
    }

    /**
     * 执行密码修改
     *
     * @return 返回执行结果
     */
    @ResponseBody
    @RequestMapping("/upass.do")
    public String exPass(String password, HttpSession session) {
        User loginUser = (User) session.getAttribute(Const.LOGIN_USER);
        User user = new User();
        user.setPassWord(password);
        user.setId(loginUser.getId());
        Integer result = userService.exPass(user);
        //清除session
        if (result != 0) {
            session.setAttribute(Const.LOGIN_USER, null);
        }
        return result.toString();
    }

    /**
     * 用户退出
     */
    @RequestMapping("/uTui")
    public String loginOut(HttpSession session) {
        session.setAttribute(Const.LOGIN_USER, null);
        return loginPage();
    }

    /**
     * 用户注册页面
     */
    @RequestMapping("/res")
    public String res() {
        return "/login/res";
    }

    /**
     * 执行用户注册
     */
    @RequestMapping("toRes")
    public String toRes(User user) {
        service.addNewUser(user);
        return loginPage();
    }
}
