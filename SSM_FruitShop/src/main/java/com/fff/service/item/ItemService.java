package com.fff.service.item;

import com.fff.pojo.Item;
import com.fff.pojo.ItemCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/11 - 12 - 11 - 17:43
 * @Description: com.fff.service
 * @version: 1.0
 */
public interface ItemService {
    /**
     * 万能查询sql
     * 主要用来分页
     *
     * @param sql
     * @return
     */
    List<Item> selectPageBySql(@Param("sql") String sql);

    /**
     * 万能更新
     *
     * @param sql
     */
    void updateBysql(@Param("sql") String sql);

    /**
     * 查询所有商品
     *
     * @return
     */
    List<Item> findAll();

    /**
     * 纪录条数
     *
     * @return
     */
    int listCount();

    /**
     * 全新分页方法！！！
     * 工具类
     *
     * @return
     */
    Map<String, Object> listPage(String input, int pageSize, String sql);

    /**
     * 实例插入
     *
     * @param object
     */
    void insertByEntry(Item object);

    /**
     * 商品图片上传
     *
     * @param object
     * @param files
     * @param req
     * @return
     */
    Item uploadItemImg(Item object, @RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest req);

    /**
     * 查找类别
     *
     * @return
     */
    List<ItemCategory> selectTypes();

    /**
     * 下架商品
     *
     * @param id
     */
    void deleteById(int id);

    /**
     * 修改商品
     *
     * @param item
     */
    void updateByEntry(Item item, @RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest req);

    /**
     * id查询单个
     */
    Item findOneById(int id);

    /**
     * 通过二级类目id查询单个商品
     */
    Item selectOneByICID(int id);
}


