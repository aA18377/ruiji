package com.fff.utils;

import com.fff.cons.Const;
import com.fff.pojo.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/31 - 12 - 31 - 19:31
 * @Description: com.fff.utils
 * @version: 1.0
 */
public class LoginUtil {

   public static void loginCheck(Model model, HttpSession session){
       User user = (User) session.getAttribute(Const.LOGIN_USER);
       if (user != null) {
           model.addAttribute("userId", user.getId());
           model.addAttribute("username", user.getUserName());
       }
   }
}
