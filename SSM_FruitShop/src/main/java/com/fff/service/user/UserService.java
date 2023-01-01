package com.fff.service.user;

import com.fff.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/10 - 12 - 10 - 22:34
 * @Description: com.fff.service
 * @version: 1.0
 */

public interface UserService {
    /**
     * 展示用户列表
     * @return
     */
    List<User> userList();

    /**
     * 分页
     * @param start
     * @param end
     * @return
     */
    List<User> userListPage(@Param("start") Integer start, @Param("end") Integer end);

    /**
     * id查询单个用户
     * @param id
     * @return
     */
    User findOneUserById(@Param("id") int id);

    /**
     * map查询多个用户
     * @param map
     * @return
     */
    List<User> findUsersByMap(Map<String,Object> map);

    /**
     * 实例查询多个用户
     * @param user
     * @return
     */
    List<User> findUsersByEntry(User user);

    /**
     * 总条数
     * @return
     */
    int UserCount();

    /**
     * 分页结果 Map
     */
    Map<String,Object> realPage(String input,int pageSize);

    /**
     * 通过用户名查询
     * @param userName
     * @return
     */
    List<User> findUserByName(String userName);

    /**
     *搜索用户功能分页
     * @param start
     * @param end
     * @return
     */
    List<User> findUserByNamePage(String userName,@Param("start") Integer start,@Param("end") Integer end);

    /**
     * 搜索用户分页参数
     * @param input
     * @param pageSize
     * @return
     */
    Map<String,Object> findUserPage(String input, int pageSize,String userName);

    /**
     * 修改用户信息
     */
    void exUpdate(User user);

    /**
     * 密码修改
     */
    Integer exPass(User user);
}
