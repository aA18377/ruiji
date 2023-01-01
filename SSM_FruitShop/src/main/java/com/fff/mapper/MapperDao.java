package com.fff.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/11 - 12 - 11 - 15:29
 * @Description: com.fff.mapper
 * @version: 1.0
 */
public interface MapperDao<T> {
    /**
     * 万能sql系列
     * 主要用来分页
     * @param sql
     * @return
     */
    List<T> selectPageBySql (@Param("sql") String sql);

    void deleteBysql(@Param("sql") String sql);

    void updateBysql(@Param("sql") String sql);

    void insertBysql(@Param("sql") String sql);

    //-------------------------------------------------------//
    /**
     * Id查询单个
     * @param id
     * @return
     */
    T findOneById(@Param("id") int id);

    /**
     * 名字查询
     * @param name
     * @return
     */
    List<T> findByName(@Param("name") String name);

    /**
     *查询所有
     * @return
     */
    List<T> findAll();


    /**
     * 分页
     * @param start
     * @param end
     * @return
     */
    List<T> page(@Param("start") Integer start,@Param("end") Integer end);

    /**
     * Id删除
     */
    void deleteById(@Param("id") int id);

    /**
     * id更新
     * @param id
     */
    void updateById(@Param("id") int id);

    /**
     * map更新
     * @param map
     */
    void updateByMap(Map<String,Object> map);

    /**
     * 对象更新
     * @param object
     */
    void updateByEntry(T object);

    /**
     * 实例插入
     * @param object
     */
    void insertByEntry(T object);

    /**
     * map插入
     * @param map
     */
    void insertByMap(Map<String,Object> map);

    /**
     * 分页By工具类
     * @param inputPage
     * @param pageSize
     * @param code
     * @return
     */
    Map<String,Object> listPage(String inputPage, int pageSize, String code);

    /**
     * 实例查询单个对象
     */
    T selectOneByEntry(T object);
}
