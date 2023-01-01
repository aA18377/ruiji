package com.fff.filter;

import com.fff.cons.Const;
import com.fff.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/9 - 12 - 09 - 20:32
 * @Description: com.fff.filter
 * @version: 1.0
 */
public class ManageLoginFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user1111 = (User) request.getSession().getAttribute(Const.ADMIN_USER);
        if (user1111 != null) {
            return true;
        }
        response.sendRedirect("/login/adminLogin");
        return false;
    }
}

