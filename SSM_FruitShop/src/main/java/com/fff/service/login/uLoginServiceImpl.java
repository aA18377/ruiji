package com.fff.service.login;

import com.fff.mapper.login.uLoginMapper;
import com.fff.pojo.Item;
import com.fff.pojo.ItemCategory;
import com.fff.pojo.NavItemCategory;
import com.fff.pojo.User;
import org.junit.Test;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/18 - 12 - 18 - 21:16
 * @Description: com.fff.service.login
 * @version: 1.0
 */
@Service
public class uLoginServiceImpl extends SqlSessionDaoSupport implements uLoginService {
    @Autowired
    private uLoginMapper mapper;

    @Override
    public boolean selectOneByEntry(User user) {
        boolean flag = false;
        User dbUser = mapper.selectOneByEntry(user);
        if (dbUser != null) {
            if (dbUser.getUserName().equals(user.getUserName()) &&
                    dbUser.getPassWord().equals(user.getPassWord())) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public User selectLoginedUser(User user) {
        return mapper.selectOneByEntry(user);
    }

    @Override
    public List<NavItemCategory> getNav(){
        return mapper.navList();
    }

    @Override
    public List<Item> zkItemList() {
        return mapper.zkItemList();
    }

    @Override
    public List<Item> fireItemList() {
        return mapper.fireItemList();
    }

    @Override
    public void addNewUser(User user) {
        mapper.addNewUser(user);
    }


}
