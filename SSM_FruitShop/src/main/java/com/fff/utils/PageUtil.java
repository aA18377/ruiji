package com.fff.utils;

import com.fff.mapper.MapperDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/8 - 12 - 08 - 18:35
 * @Description: com.fff.utils
 * @version: 1.0
 */

public class PageUtil {

    /**
     * 判断跳转页数是否合法
     *
     * @param input          要跳转页数
     * @param totalPageCount 最大页面数
     * @return 返回正确页数
     */
    public static Integer inputPage(String input, int totalPageCount) {
        Integer index = null;
        if (input != null && !"".equals(input)) {
            index = Integer.parseInt(input);
            if (index < 1) {
                index = 1;
            } else if (index > totalPageCount) {
                index = totalPageCount;
            }
        }

        return index;
    }

    /**
     * 分页公共参数
     * @param input 需要跳转页面
     * @param pageSize  页面容量
     * @param totalCount 数据库条数
     * @return
     */
    public static Map<String,Object> getPageParm(String input, int pageSize,Integer totalCount){
        //总页数
        Integer totalPageCount = totalCount / pageSize + 1;
        //真实跳转页数
        Integer index = PageUtil.inputPage(input, totalPageCount);

        Integer start = 0;
        Integer end = pageSize-1;
        if (index != null) {
            start = (index - 1) * pageSize;
            end = index * pageSize - 1;
        }

        //当前页面
        int currentPageNo = 1;
        if (index != null){
            currentPageNo = index;
        }
        Map<String, Object> map = new HashMap<>(6);
        map.put("totalPageCount",totalPageCount);
        map.put("start",start);
        map.put("end",end);
        map.put("currentPageNo",currentPageNo);
        return map;
    }

    /**
     * 通过自义定sql完成分页
     * 使用该方法需要先在mapper根据返回的类型写一个纯sql，名字要统一为selectPageBySql
     * 使用此方法需要先实现MapperDao接口
     * @param input
     * @param pageSize
     * @param sql
     * @return
     */
    public static Map<String, Object> PageBySql(String input, int pageSize, String sql, MapperDao dao) {
        //总记录条数
        Integer totalCount = dao.selectPageBySql(sql).size();
        Map<String, Object> map = PageUtil.getPageParm(input, pageSize, totalCount);
        System.out.println(map.get("start"));
        map.put("totalCount",totalCount);
        Integer start = (Integer) map.get("start");
        Integer end = (Integer) map.get("end");
        sql = sql+" limit "+start+","+end;
        List<?> list = dao.selectPageBySql(sql);
        map.put("datas",list);
        return map;
    }


}
