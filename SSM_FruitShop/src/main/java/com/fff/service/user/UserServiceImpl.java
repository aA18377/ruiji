package com.fff.service.user;

import com.fff.mapper.user.UserMapper;
import com.fff.pojo.User;
import com.fff.utils.PageUtil;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/10 - 12 - 10 - 22:36
 * @Description: com.fff.service
 * @version: 1.0
 */
@Service
public class UserServiceImpl extends SqlSessionDaoSupport implements UserService {
    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> userList() {
        return mapper.userList();
    }

    @Override
    public List<User> userListPage(Integer start, Integer end) {
        return mapper.userListPage(start,end);
    }

    @Override
    public User findOneUserById(int id) {
        return mapper.findOneUserById(id);
    }

    @Override
    public List<User> findUsersByMap(Map<String, Object> map) {
        return mapper.findUsersByMap(map);
    }

    @Override
    public List<User> findUsersByEntry(User user) {
        return mapper.findUsersByEntry(user);
    }

    @Override
    public int UserCount() {
        return userList().size();
    }

    @Override
    public Map<String, Object> realPage(String input, int pageSize) {
        //总记录条数
        Integer totalCount = UserCount();
        Map<String, Object> map = PageUtil.getPageParm(input, pageSize, totalCount);
        map.put("totalCount",totalCount);
        Integer start = (Integer) map.get("start");
        Integer end = (Integer) map.get("end");
        List<User> list = userListPage(start, end);
        map.put("datas",list);
        return map;
    }

    @Override
    public List<User> findUserByName(String userName) {
        return mapper.findUserByName(userName);
    }

    @Override
    public List<User> findUserByNamePage(String userName, Integer start, Integer end) {
        return mapper.findUserByNamePage(userName,start,end);
    }


    @Override
    public Map<String, Object> findUserPage(String input, int pageSize,String userName) {
        userName = "%"+userName+"%";
        //总记录条数
        Integer totalCount = findUserByName(userName).size();
        Map<String, Object> map = PageUtil.getPageParm(input, pageSize, totalCount);
        map.put("totalCount",totalCount);
        Integer start = (Integer) map.get("start");
        Integer end = (Integer) map.get("end");
        List<User> list = findUserByNamePage(userName,start, end);
        map.put("datas",list);
        return map;
    }

    @Override
    public void exUpdate(User user) {
        mapper.updateByEntry(user);
    }

    @Override
    public Integer exPass(User user) {
        return mapper.exPass(user);
    }


}
