package com.yan.filter;

import com.yan.constant.Constant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: zhaoss
 * @Date: 2022/9/2 - 09 - 02 - 19:47
 * @Description: com.yan.filter
 * @version: 1.0
 */
public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Object userSession = req.getSession().getAttribute(Constant.USERSESSION);
        if (userSession == null) {
            resp.sendRedirect("/error.jsp");
        }
        filterChain.doFilter(req, resp);
    }

    public void destroy() {

    }
}
