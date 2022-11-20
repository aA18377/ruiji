package com.yan.servlet;

import com.alibaba.fastjson.JSONObject;
import com.yan.constant.Constant;
import com.yan.pojo.SmbmsRole;
import com.yan.pojo.SmbmsUser;
import com.yan.service.UserService;
import com.yan.service.UserServiceIml;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/9/3 - 09 - 03 - 14:30
 * @Description: com.yan.servlet
 * @version: 1.0
 */
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService service = new UserServiceIml();
        //判断页面请求路径
        String method = req.getParameter("method");
        if ("pwdmodify".equals(method)) {
            CheckOldPassword(service, req, resp);
        } else if ("query".equals(method)) {
            UserManger(service, req, resp);
        } else if ("savepwd".equals(method)) {
            Password(service, req, resp);
        } else if ("getrolelist".equals(method)) {
            addUserRole(service, resp);
        } else if ("ucexist".equals(method)) {
            userExist(service, req, resp);
        } else if ("add".equals(method)) {
            userAdd(service, req, resp);
        } else if ("deluser".equals(method)) {
            deleteUser(service, req, resp);
        } else if ("view".equals(method)) {
            showView(service, req, resp);
        } else if ("modify".equals(method)) {
            updateUserView(service,req, resp);
        } else if ("modifyexe".equals(method)) {
            updateUser(service, req, resp);
        }

    }

    //修改用户
    public void updateUser(UserService service, HttpServletRequest req, HttpServletResponse resp) {
        String userName = req.getParameter("userName");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        Long uid = (Long) req.getSession().getAttribute("uid");
        String userRole = req.getParameter("userRole");
        Map<String, Object> map = new HashMap<String, Object>(7);
        System.out.println("uid=" + uid);
        map.put("uid", uid);
        map.put("userName", userName);
        map.put("gender", Long.parseLong(gender));
        map.put("birthday", Date.valueOf(birthday));
        map.put("phone", phone);
        map.put("address", address);
        map.put("userRole", Long.parseLong(userRole));
        service.updateUser(map);
        //跳转
        UserManger(service, req, resp);

    }

    //修改用户视图
    public void updateUserView(UserService service,HttpServletRequest req, HttpServletResponse resp) {
        String uid = req.getParameter("uid");
        HttpSession session = req.getSession();
        SmbmsUser smbmsUser = service.userInfo(Integer.parseInt(uid));
        req.setAttribute("user",smbmsUser);
        session.setAttribute("uid", Long.parseLong(uid));
        try {
            req.getRequestDispatcher("usermodify.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //用户视图
    public void showView(UserService service, HttpServletRequest req, HttpServletResponse resp) {
        String uid = req.getParameter("uid");
        SmbmsUser smbmsUser = service.userInfo(Integer.parseInt(uid.trim()));
        req.setAttribute("user", smbmsUser);
        try {
            req.getRequestDispatcher("userview.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    //旧密码验证
    public void CheckOldPassword(UserService changePassword, HttpServletRequest req, HttpServletResponse resp) {
        changePassword.checkOldPassword(req, resp);
    }

    //添加新用户身份
    public void addUserRole(UserService service, HttpServletResponse resp) {
        service.addUserRole(resp);
    }

    //检查用户是否存在
    public void userExist(UserService service, HttpServletRequest req, HttpServletResponse resp) {
        service.userCodeExits(req, resp);
    }

    //用户管理

    public void UserManger(UserService service, HttpServletRequest req, HttpServletResponse resp) {
        /**
         * @pageIndexNO 前端输入的要跳转页数
         * @currentPageNo 显示的当前页面数
         * @pagesize 数据库查询的最大页面容量
         * @totalPageCount 显示的最大页面数
         * @totalCount 显示的纪录条数
         */
        //前端参数
        String queryname = req.getParameter("queryname");
        String queryUserRole = req.getParameter("queryUserRole");
        String pageIndexNO = req.getParameter("pageIndex"); //跳转页数
        //默认查询页数
        int currentPageNo = 1;
        int pagesize = 4;  //页面容量
        //默认身份选项
        int roleId = 0;
        if (queryUserRole != null && !queryUserRole.equals("")) {
            roleId = Integer.parseInt(queryUserRole.trim());
        }
        //纪录条数（从零开始）
        Map<String, Object> countMap = new HashMap<String, Object>(2);
        countMap.put("userName", queryname);
        countMap.put("userRole", roleId);
        int totalCount = service.ShowNumByNameOrById(countMap);
        //总页面数
        int totalPageCount = totalCount / pagesize + 1;
        //默认起始页
        int pageIndex = 0;
        if (pageIndexNO != null && !pageIndexNO.equals("")) {
            pageIndex = Integer.parseInt(pageIndexNO.trim());
            //页数检测
            if (pageIndex < 1) {
                pageIndex = 1;
            } else if (pageIndex > totalPageCount) {
                pageIndex = totalPageCount;
            }
            pageIndex = (currentPageNo - 1) * pageIndex;
            currentPageNo = Integer.parseInt(pageIndexNO);
        }
        //用户身份
        List<SmbmsRole> roleList = service.showRoleName();
        Map<String, Object> pageUserMap = new HashMap<String, Object>(4);
        pageUserMap.put("userName", queryname);
        pageUserMap.put("userRole", roleId);
        pageUserMap.put("totalPageCount", pageIndex);  //跳转页面
        pageUserMap.put("pageSize", pagesize);  //容量
        List<SmbmsUser> userList = service.ShowUserList(pageUserMap);

        //空搜索结果，方便返回前端
        if (queryname == null) {
            queryname = "";
        }
        //将数据传回前端
        req.setAttribute("userList", userList);
        req.setAttribute("totalPageCount", totalPageCount);  //最大页数
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("roleList", roleList);
        req.setAttribute("currentPageNo", currentPageNo); //当前页面
        req.setAttribute("queryUserName", queryname);
        req.setAttribute("queryUserRole", roleId);

        try {
            req.getRequestDispatcher("userlist.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //密码修改
    public void Password(UserService service, HttpServletRequest req, HttpServletResponse resp) {
        boolean flag = false;
        String newpassword = req.getParameter("newpassword");
        HttpSession session = req.getSession();
        SmbmsUser user = (SmbmsUser) session.getAttribute(Constant.USERSESSION);
        user.setUserPassword(newpassword);
        flag = service.changePassword(user);
        if (flag) {
            req.setAttribute("message", "修改密码成功，请返回登陆界面重新登陆");
            session.removeAttribute(Constant.USERSESSION);
        } else {
            if (newpassword == null) {
                req.setAttribute("message", "新密码为空");
            } else {
                req.setAttribute("message", "旧密码不正确");
            }
        }

        try {
            req.getRequestDispatcher("pwdmodify.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //添加用户
    public void userAdd(UserService service, HttpServletRequest req, HttpServletResponse response) {
        //获取参数
        String userCode = req.getParameter("userCode");
        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPassword");
        String phone = req.getParameter("phone");
        String birthday = req.getParameter("birthday");
        String userRole = req.getParameter("userRole");
        //构建对象
        SmbmsUser user = new SmbmsUser();
        user.setUserPassword(userPassword);
        user.setUserName(userName);
        user.setUserCode(userCode);
        user.setPhone(phone);
        user.setBirthday(Date.valueOf(birthday));
        user.setUserRole(Long.parseLong(userRole.trim()));
        service.addUser(user);
        try {
            response.sendRedirect("userlist.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //删除用户
    public void deleteUser(UserService service, HttpServletRequest req, HttpServletResponse resp) {
        String userid = req.getParameter("uid");
        int i = 0;
        if (userid != null) {
            i = service.deleteUser(Integer.parseInt(userid));
        }
        Map<String, String> map = new HashMap<String, String>(1);
        if (i == 1) {
            map.put("delResult", "true");
        } else if (i == -1) {
            map.put("delResult", "notexist");
        } else {
            map.put("delResult", "false");
        }
        String s = JSONObject.toJSONString(map);
        resp.setContentType("application/json");
        try {
            PrintWriter writer = resp.getWriter();
            writer.print(s);
            writer.flush();
            writer.close();
            //缺个自动刷新
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
