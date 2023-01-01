package com.fff.mapper.login;

import com.fff.pojo.Manage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/8 - 12 - 08 - 15:28
 * @Description: com.fff.mapper
 * @version: 1.0
 */
@Repository
public interface MLoginMapper {



    /**
     * 通过实例查询多个对象
     * 通过实例查询单个对象
     * @param manage
     * @return
     */
    List<Manage> findListByEntry(Manage manage);

}
