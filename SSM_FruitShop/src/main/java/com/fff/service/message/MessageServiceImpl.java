package com.fff.service.message;

import com.fff.mapper.message.MessageMapper;
import com.fff.mapper.news.NewsMapper;
import com.fff.pojo.Message;
import com.fff.pojo.News;
import com.fff.utils.PageUtil;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/17 - 12 - 17 - 21:57
 * @Description: com.fff.service.message
 * @version: 1.0
 */
@Service
public class MessageServiceImpl extends SqlSessionDaoSupport implements MessageService{

    @Autowired
    private MessageMapper mapper;

    @Override
    public List<Message> selectPageBySql(String sql) {
        return mapper.selectPageBySql(sql);
    }

    @Override
    public List<Message> findAll() {
        return mapper.findAll();
    }

    @Override
    public void insertByEntry(Message message) {
        mapper.insertByEntry(message);
    }

    @Override
    public Map<String, Object> listDetailPage(String inputPage, int pageSize, String name) {
        String sql = "";
        Map<String, Object> map = null;
        sql = "";
        sql = "select * from message";
        if (name != null){
            sql = "select * from message where name like \"%"+name+"%\"";
        }
        map = PageUtil.PageBySql(inputPage, pageSize, sql, mapper);
        return map;
    }
}
