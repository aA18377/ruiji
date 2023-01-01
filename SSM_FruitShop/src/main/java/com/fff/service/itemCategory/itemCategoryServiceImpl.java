package com.fff.service.itemCategory;

import com.fff.mapper.itemCategory.itemCategoryMapper;
import com.fff.pojo.Item;
import com.fff.pojo.ItemCategory;
import com.fff.utils.PageUtil;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/8 - 12 - 08 - 18:22
 * @Description: com.fff.service
 * @version: 1.0
 */

@Repository
public class itemCategoryServiceImpl extends SqlSessionDaoSupport implements itemCategoryService {

    /**
     * mapper接口应向上提取出一个抽象类，这样就不用每次都注入了
     */
    @Autowired
    private itemCategoryMapper mapper;

    @Override
    public List<ItemCategory> findListAll() {
        return mapper.findListAll();
    }

    //按理来说这个应该是私有的
    @Override
    public List<ItemCategory> findListByPage(Integer start, Integer end) {
        return mapper.findListByPage(start,end);
    }

    @Override
    public Integer findListCount() {
        List<ItemCategory> listAll = findListAll();
        return listAll.size();
    }

    /**
     *
     * @param input  跳转页面
     * @param pageSize  最大页面容量
     * @return
     */
    @Override
    public Map<String,Object> listPage(String input, int pageSize) {
        //总记录条数
        Integer totalCount = findListCount();
        Map<String, Object> map = PageUtil.getPageParm(input, pageSize, totalCount);
        map.put("totalCount",totalCount);
        Integer start = (Integer) map.get("start");
        Integer end = (Integer) map.get("end");
        List<ItemCategory> list = findListByPage(start, end);
        map.put("datas",list);
        return map;
    }

    @Override
    public void additemCategoryOne(ItemCategory itemCategory) {
        if (itemCategory.getName() != null){
            itemCategory.setIsDelete(0L);
            itemCategory.setPid(0L);
            mapper.additemCategoryOne(itemCategory);
        }
    }

    @Override
    public void deleteItemById(int id) {
        mapper.deleteItemById(id);
    }

    @Override
    public void updateItemId(ItemCategory itemCategory) {
        if (itemCategory.getName() != null && !"".equals(itemCategory.getName())){
            mapper.updateItemId(itemCategory);
        }
    }

    @Override
    public ItemCategory findOneById(int id) {
        return mapper.findOneById(id);
    }


    @Override
    public List<ItemCategory> findItem2(int id, Integer start, Integer end) {
        return mapper.findItem2(id,start,end);
    }

    @Override
    public List<ItemCategory> listItem2(int id) {
        return mapper.listItem2(id);
    }

    @Override
    public int listItem2Count(int id) {
        return listItem2(id).size();
    }

    @Override
    public Map<String, Object> pageItem2(int id, String input, int pageSize) {
        //总记录条数
        Integer totalCount = listItem2Count(id);
        Map<String, Object> map = PageUtil.getPageParm(input, pageSize, totalCount);
        map.put("totalCount",totalCount);
        Integer start = (Integer) map.get("start");
        Integer end = (Integer) map.get("end");
        List<ItemCategory> list = findItem2(id,start, end);
        map.put("datas",list);
        return map;
    }

    @Override
    public void addItem2(ItemCategory itemCategory) {
            mapper.addItem2(itemCategory);
    }

    @Override
    public void updateItem2(ItemCategory itemCategory) {
        mapper.updateItem2(itemCategory);
    }

    @Override
    public void deleteItem2(int id) {
        mapper.deleteItem2(id);
    }

    @Override
    public List<ItemCategory> superSelect(String sql) {
        return mapper.superSelect(sql);
    }

    @Override
    public List<Item> selectItem(String sql) {
        return mapper.selectItem(sql);
    }


}
