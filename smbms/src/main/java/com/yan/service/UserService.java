package com.yan.service;

import com.yan.pojo.SmbmsRole;
import com.yan.pojo.SmbmsUser;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/9/6 - 09 - 06 - 10:31
 * @Description: com.yan.service
 * @version: 1.0
 */
public interface UserService {
    SmbmsUser getLogin(String userCode);
    boolean changePassword(SmbmsUser user);
    void checkOldPassword(HttpServletRequest request, HttpServletResponse response);
    int ShowNumByNameOrById(Map<String,Object> map);
    List<SmbmsUser> ShowUserList(Map<String,Object> map);
    List<SmbmsRole> showRoleName();
    void addUser(SmbmsUser user);
    void addUserRole(HttpServletResponse response);
    void userCodeExits(HttpServletRequest req,HttpServletResponse resp);
    int deleteUser(int userid);
    SmbmsUser userInfo(int uid);
    void updateUser(Map<String,Object> map);
}
