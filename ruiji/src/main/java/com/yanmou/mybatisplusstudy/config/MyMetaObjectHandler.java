package com.yanmou.mybatisplusstudy.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.yanmou.mybatisplusstudy.pojo.Employee;
import com.yanmou.mybatisplusstudy.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @Auther: zhaoss
 * @Date: 2023/2/26 - 02 - 26 - 0:13
 * @Description: com.yanmou.mybatisplusstudy.config
 * @version: 1.0
 */

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Autowired
    private HttpSession httpSession;

    public long getUserId() {
        long id = 0L;
        Employee loginUser = (Employee) httpSession.getAttribute("loginUser");
        if (loginUser!=null){
            id = loginUser.getId();
        }
        User visitUser = (User) httpSession.getAttribute("visitUser");
        id = visitUser.getId();
        return id;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter("createTime")) {
            metaObject.setValue("createTime", new Timestamp(System.currentTimeMillis()));
        }
        if (metaObject.hasSetter("updateTime")) {
            metaObject.setValue("updateTime", new Timestamp(System.currentTimeMillis()));
        }
        if (metaObject.hasSetter("createUser")) {
            metaObject.setValue("createUser", getUserId());
        }
        if (metaObject.hasSetter("updateUser")) {
        metaObject.setValue("updateUser", getUserId());
        }
        if (metaObject.hasSetter("isDeleted")) {
            metaObject.setValue("isDeleted", 0);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        metaObject.setValue("updateTime", new Timestamp(System.currentTimeMillis()));
        metaObject.setValue("updateUser", getUserId());
    }
}
