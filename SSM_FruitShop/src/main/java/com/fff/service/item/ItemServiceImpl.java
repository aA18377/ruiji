package com.fff.service.item;

import com.fff.mapper.item.ItemMapper;
import com.fff.pojo.Item;
import com.fff.pojo.ItemCategory;
import com.fff.utils.PageUtil;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/11 - 12 - 11 - 17:44
 * @Description: com.fff.service
 * @version: 1.0
 */
@Service
public class ItemServiceImpl extends SqlSessionDaoSupport implements ItemService {
    @Autowired
    private ItemMapper mapper;

    @Override
    public List<Item> selectPageBySql(String sql) {
        return mapper.selectPageBySql(sql);
    }

    @Override
    public void updateBysql(String sql) {
        mapper.updateBysql(sql);
    }

    @Override
    public List<Item> findAll() {
        return mapper.findAll();
    }

    @Override
    public int listCount() {
        return findAll().size();
    }

    @Override
    public Map<String, Object> listPage(String input, int pageSize,String sql) {
        Map<String, Object> map = PageUtil.PageBySql(input,pageSize,sql,mapper);
        return map;
    }

    @Override
    public void insertByEntry(Item object) {
        if (object.getZk() > 9){
            object.setZk(9);
        }else if (object.getZk() < 0){
            object.setZk(0);
        }
        mapper.insertByEntry(object);
    }

    @Override
    public Item uploadItemImg(Item object, @RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest req) {
        String realPath = req.getSession().getServletContext().getRealPath("\\");
        if (files.length > 0){
            for (int i = 0; i < files.length; i++) {
                if (files[i].getSize() <= 0){
                    continue;
                }
                String random  = UUID.randomUUID().toString();
                String uploadPth = realPath+"\\reourcesHtml\\upload\\";
                File upload = new File(uploadPth);
               if (!upload.exists()){
                   try {
                       upload.createNewFile();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
                String path = uploadPth+random+files[i].getOriginalFilename();
                File newFile = new File(path);
                try {
                    files[i].transferTo(newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                switch (i){
                    case 0:
                        object.setUrl1(req.getContextPath()+"\\reourcesHtml\\upload\\"+random+files[i].getOriginalFilename());
                        break;
                    case 1:
                        object.setUrl2(req.getContextPath()+"\\reourcesHtml\\upload\\"+random+files[i].getOriginalFilename());
                        break;
                    case 2:
                        object.setUrl3(req.getContextPath()+"\\reourcesHtml\\upload\\"+random+files[i].getOriginalFilename());
                        break;
                    case 3:
                        object.setUrl4(req.getContextPath()+"\\reourcesHtml\\upload\\"+random+files[i].getOriginalFilename());
                        break;
                    case 4:
                        object.setUrl5(req.getContextPath()+"\\reourcesHtml\\upload\\"+random+files[i].getOriginalFilename());
                        break;
                }
            }
        }
        return object;
    }

    @Override
    public List<ItemCategory> selectTypes() {
        return mapper.selectTypes();
    }

    @Override
    public void deleteById(int id) {
        mapper.deleteById(id);
    }

    @Override
    public void updateByEntry(Item item, @RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest req) {
        Item updateItem = uploadItemImg(item, files, req);
        mapper.updateByEntry(updateItem);
    }

    @Override
    public Item findOneById(int id) {
        return mapper.findOneById(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Item selectOneByICID(int id) {
        return mapper.selectOneByICID(id);
    }



}

