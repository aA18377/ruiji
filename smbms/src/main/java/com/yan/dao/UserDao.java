package com.yan.dao;

import com.yan.pojo.SmbmsRole;
import com.yan.pojo.SmbmsUser;
import org.apache.ibatis.annotations.Param;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/9/2 - 09 - 02 - 9:48
 * @Description: com.yan.dao
 * @version: 1.0
 */
public interface UserDao {
    //获取登陆用户
    SmbmsUser getLoginUser(String userCode);
    //修改用户密码
    boolean changPassword(SmbmsUser user);
    //根据用户名和身份id返回人数
    int showNumber(Map<String,Object> map);
    //显示职位
    List<SmbmsRole> showRoleName();
    //分页获取用户信息
    List<SmbmsUser> showUser(Map<String,Object> map);
    //添加用户
    boolean addUser(SmbmsUser user);
    //删除用户
    int deleteUser(@Param("userid") int userid);
    //用户删除存在检查
    SmbmsUser deleteUserExist(@Param("userid") int userid);
    //修改用户信息
    void updateUser(Map<String,Object> map);
}
