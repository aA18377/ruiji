package com.fff.service.login;

import com.fff.pojo.Manage;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/8 - 12 - 08 - 15:44
 * @Description: com.fff.service
 * @version: 1.0
 */
public interface MLoginService {
    /**
     * 通过实例查询单个对象
     * @param manage
     * @return
     */
    boolean findOneByEntry(Manage manage);

    /**
     * 通过实例查询多个对象
     * @param manage
     * @return
     */
    List<Manage> findListByEntry(Manage manage);
}
