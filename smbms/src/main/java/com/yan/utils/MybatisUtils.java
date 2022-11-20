package com.yan.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: zhaoss
 * @Date: 2022/9/26 - 09 - 26 - 23:58
 * @Description: com.yan.utils
 * @version: 1.0
 */
public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory = null;
    static{
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        String resources = "mybatis-config.xml";
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream(resources);
            sqlSessionFactory= sqlSessionFactoryBuilder.build(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SqlSession getSqlsession(){
        return sqlSessionFactory.openSession();
    }
}
