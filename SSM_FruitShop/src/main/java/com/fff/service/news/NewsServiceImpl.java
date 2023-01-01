package com.fff.service.news;

import com.fff.mapper.MapperDao;
import com.fff.mapper.news.NewsMapper;
import com.fff.pojo.News;
import com.fff.pojo.OrderDetail;
import com.fff.utils.PageUtil;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/17 - 12 - 17 - 20:50
 * @Description: com.fff.service.news
 * @version: 1.0
 */
@Service
public class NewsServiceImpl extends SqlSessionDaoSupport implements NewsService {
   @Autowired
    private NewsMapper mapper;

    @Override
    public List<News> selectPageBySql(String sql) {
        return mapper.selectPageBySql(sql);
    }

    @Override
    public List<News> findAll() {
        return mapper.findAll();
    }

    @Override
    public Map<String, Object> listDetailPage(String inputPage, int pageSize,String name) {
        String sql = "";
        Map<String, Object> map = null;
        sql = "";
        sql = "select * from news";
        if (name != null){
            sql = "select * from news where name like \"%"+name+"%\"";
        }
        map = pages(inputPage, pageSize, sql);
        return map;
    }



    @Override
    public void deleteById(int id) {
        mapper.deleteById(id);
    }

    @Override
    public void updateByEntry(News object) {
        mapper.updateByEntry(object);
    }

    @Override
    public void insertByEntry(News object) {
        mapper.insertByEntry(object);
    }

    @Override
    public News findOneById(int id) {
        return mapper.findOneById(id);
    }

    @Override
    public Map<String, Object> pages(String inputPage, int pageSize, String sql) {
        return PageUtil.PageBySql(inputPage, pageSize, sql, mapper);
    }
}
