package com.fff.service.news;

import com.fff.mapper.MapperDao;
import com.fff.pojo.News;
import com.fff.pojo.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/17 - 12 - 17 - 20:49
 * @Description: com.fff.service.news
 * @version: 1.0
 */
public interface NewsService {
    /**
     * 万能搜索sql
     * @param sql
     * @return
     */
    List<News> selectPageBySql(String sql);

    /**
     * 查询所有
     * @return
     */
    List<News> findAll();




    /**
     * 订单详情分页
     * @param inputPage
     * @param pageSize
     * @return
     */
    Map<String,Object> listDetailPage(String inputPage, int pageSize,String name);

    /**
     * Id删除
     */
    void deleteById(@Param("id") int id);

    /**
     * 对象更新
     * @param object
     */
    void updateByEntry(News object);

    /**
     * 实例插入
     * @param object
     */
    void insertByEntry(News object);

    /**
     * id查询单用户
     */
    News findOneById(int id);

    /**
     * 分页功能
     */
    Map<String,Object> pages(String inputPage, int pageSize, String sql);
}
