package com.fff.service.login;

import com.fff.mapper.login.MLoginMapper;
import com.fff.pojo.Manage;
import com.fff.utils.MyRepeatException;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/8 - 12 - 08 - 15:45
 * @Description: com.fff.service
 * @version: 1.0
 */
@Service
public class MLoginServiceImpl extends SqlSessionDaoSupport implements MLoginService {

    @Autowired
    private MLoginMapper mapper;

    @Override
    public boolean findOneByEntry(Manage manage) {
        List<Manage> list = findListByEntry(manage);
        boolean flag = false;
        try {
            if (list.size() == 1) {
                Iterator<Manage> iterator = list.iterator();
                Manage aManage = iterator.next();
                if (aManage.getUserName().equals(manage.getUserName()) &&
                        aManage.getPassWord().equals(manage.getPassWord())) {
                    flag = true;
                }
            } else {
                throw new MyRepeatException("登录用户不唯一");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<Manage> findListByEntry(Manage manage) {
        return mapper.findListByEntry(manage);
    }
}
