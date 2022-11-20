package com.yan.service;

import com.alibaba.fastjson.JSONObject;
import com.yan.constant.Constant;
import com.yan.dao.UserDao;
import com.yan.pojo.SmbmsRole;
import com.yan.pojo.SmbmsUser;
import com.yan.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/9/6 - 09 - 06 - 10:32
 * @Description: com.yan.service
 * @version: 1.0
 */
public class UserServiceIml implements UserService {
    //获取登陆用户
    public SmbmsUser getLogin(String userCode) {
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        SmbmsUser smbmsUser = null;
        UserDao mapper = sqlsession.getMapper(UserDao.class);
        smbmsUser = mapper.getLoginUser(userCode);
        if (smbmsUser.getUserCode() != null) {
            return smbmsUser;
        }
        sqlsession.close();
        return null;
    }


    @Test
    public void getLogintest(){
        UserServiceIml userServiceIml = new UserServiceIml();
        SmbmsUser demo = userServiceIml.getLogin("demo");
        System.out.println(demo);
    }

    //更改用户密码
    public boolean changePassword(SmbmsUser user) {
        boolean flag = false;
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        UserDao mapper = sqlsession.getMapper(UserDao.class);
        if (user.getUserPassword() != null) {
            mapper.changPassword(user);
            flag = true;
            sqlsession.commit();
        }
        sqlsession.close();
        return flag;
    }

    //通过Ajax验证旧密码
    public void checkOldPassword(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //数据
        String oldpassword = request.getParameter("oldpassword"); //前端数据
        SmbmsUser user = (SmbmsUser) session.getAttribute(Constant.USERSESSION);
        String userPassword = user.getUserPassword();//session中用户密码
        //判断
        //noinspection AlibabaCollectionInitShouldAssignCapacity
        Map<String, String> resultMap = new HashMap<String, String>();
        if (session == null) {
            resultMap.put("result", "sessionerror");
        } else if (oldpassword == null) {
            resultMap.put("result", "error");
        } else {
            if (oldpassword.equals(userPassword)) {
                resultMap.put("result", "true");
            } else {
                resultMap.put("result", "false");
            }
        }
        //转换map为json字符串---初版
//        StringBuffer JsonString = new StringBuffer("{");
//        for (Map.Entry entry : resultMap.entrySet()) {
//            JsonString.append("\""+entry.getKey() + "\":\"" + entry.getValue() + "\",");
//        }
//        JsonString.deleteCharAt(JsonString.length() - 1);
//        JsonString.append("}");
        String s = JSONObject.toJSONString(resultMap);
        try {
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            writer.write(s);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //根据用户名或身份返回纪录条数
    public int ShowNumByNameOrById(Map<String,Object> map) {
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        UserDao mapper = sqlsession.getMapper(UserDao.class);
        int number = mapper.showNumber(map);
        sqlsession.close();
        return number;
    }

    //分页，获取用户信息
    public List<SmbmsUser> ShowUserList(Map<String,Object> map) {
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        UserDao mapper = sqlsession.getMapper(UserDao.class);
        //放回列表
        List<SmbmsUser> userList = mapper.showUser(map);
        sqlsession.close();
        return userList;
        }


    //获取身份
    public List<SmbmsRole> showRoleName() {
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        UserDao mapper = sqlsession.getMapper(UserDao.class);
        List<SmbmsRole> smbmsRoles = mapper.showRoleName();
        List<SmbmsRole> roleList = new ArrayList<SmbmsRole>(3);
        for (SmbmsRole smbmsRole : smbmsRoles) {
            roleList.add(smbmsRole);
        }
        sqlsession.close();
        return roleList;
    }

    //添加用户
    public void addUser(SmbmsUser user){
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        UserDao mapper = sqlsession.getMapper(UserDao.class);
        mapper.addUser(user);
        sqlsession.commit();
        sqlsession.close();
    }

    //添加新用户的身份
    public void addUserRole(HttpServletResponse response){
        List<SmbmsRole> roleList = showRoleName();
        String s = JSONObject.toJSONString(roleList);
        try {
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            writer.write(s);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //检查用户是否已经存在
    public void userCodeExits(HttpServletRequest req,HttpServletResponse resp){
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        UserDao mapper = sqlsession.getMapper(UserDao.class);
        String userCode = req.getParameter("userCode");
        SmbmsUser loginUser = null;
        //非空判断
        if (userCode != null){
            loginUser = mapper.getLoginUser(userCode);
        }
        Map<String,Object> map = new HashMap<String, Object>(1);
        if (loginUser != null){
            map.put("userCode","exist");
        }else if("".equals(userCode)){
            map.put("userCode","false");
        }
        else{
            map.put("userCode","true");
        }
        String s = JSONObject.toJSONString(map);
        resp.setContentType("application/json");
        try {
            PrintWriter writer = resp.getWriter();
            writer.write(s);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sqlsession.close();
    }

    //删除用户
    public int deleteUser(int userid){
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        UserDao mapper = sqlsession.getMapper(UserDao.class);
        SmbmsUser smbmsUser = mapper.deleteUserExist(userid);
        int i = -1;
        if (smbmsUser != null){
         i = mapper.deleteUser(userid);
        }
        sqlsession.commit();
        sqlsession.close();
        return i;
    }

    //查看用户信息
    public SmbmsUser userInfo(int uid){
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        UserDao mapper = sqlsession.getMapper(UserDao.class);
        SmbmsUser smbmsUser = mapper.deleteUserExist(uid);//通用
        sqlsession.close();
        return smbmsUser;
    }

    //修改用户信息
    public void updateUser(Map<String,Object> map){
        SqlSession sqlsession = MybatisUtils.getSqlsession();
        UserDao mapper = sqlsession.getMapper(UserDao.class);
        mapper.updateUser(map);
        sqlsession.commit();
        sqlsession.close();
    }

}


