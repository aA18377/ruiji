package com.fff.service.message;

import com.fff.pojo.Message;
import com.fff.pojo.News;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/17 - 12 - 17 - 21:57
 * @Description: com.fff.service.message
 * @version: 1.0
 */
public interface MessageService {
    /**
     * 万能搜索sql
     * @param sql
     * @return
     */
    List<Message> selectPageBySql(String sql);

    /**
     * 查询所有
     * @return
     */
    List<Message> findAll();

    /**
     * 实例插入
     */
    void insertByEntry(Message message);



    /**
     * 订单详情分页
     * @param inputPage
     * @param pageSize
     * @return
     */
    Map<String,Object> listDetailPage(String inputPage, int pageSize, String name);
}
