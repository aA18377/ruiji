package com.fff.mapper.user;

import com.fff.mapper.MapperDao;
import com.fff.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/9 - 12 - 09 - 21:06
 * @Description: com.fff.mapper
 * @version: 1.0
 */
@Repository
public interface UserMapper extends MapperDao<User> {

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
    List<User> userListPage(@Param("start") Integer start,@Param("end") Integer end);

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
     * 通过用户名查询用户
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
    List<User> findUserByNamePage(@Param("userName") String userName,@Param("start") Integer start,@Param("end") Integer end);

    /**
     * 修改密码
     * @param user
     * @return
     */
    Integer exPass(User user);
}
