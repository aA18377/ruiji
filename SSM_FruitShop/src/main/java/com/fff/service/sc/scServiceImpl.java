package com.fff.service.sc;

import com.fff.cons.Const;
import com.fff.mapper.sc.scMapper;
import com.fff.pojo.Item;
import com.fff.pojo.User;
import net.sf.jsqlparser.expression.operators.arithmetic.Concat;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fff.pojo.Sc;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/25 - 12 - 25 - 22:16
 * @Description: com.fff.service.sc
 * @version: 1.0
 */
@Service
public class scServiceImpl extends SqlSessionDaoSupport implements scService {
    @Autowired
    private scMapper mapper;

    @Override
    public List<Sc> scList() {
        return mapper.findAll();
    }

    @Override
    public void deleteById(int id) {
        mapper.deleteById(id);
    }

    @Override
    public void scAdd(int id) {
        if (id > 0 ) {
            Item oneById = mapper.findOneByIdForItem(id);
            oneById.setScNum(oneById.getScNum() + 1);
            mapper.scAdd(oneById);
        }
    }

    @Override
    public Item findOneByIdForItem(int id) {
        return mapper.findOneByIdForItem(id);
    }


    @Override
    public void insertByEntry(int itemId, User loginUser) {
        //不重复才能添加
        if (!repactSc(loginUser.getId(),itemId)) {
            if (itemId > 0) {
                Item item = findOneByIdForItem(itemId);
                if (loginUser != null) {
                    Sc sc = new Sc();
                    sc.setItem(item);
                    sc.setItemId(item.getId());
                    sc.setUserId(loginUser.getId());
                    mapper.insertByEntry(sc);
                }
            }
        }
    }

    /**
     * true为重复
     * @param item_id
     * @return
     */
    @Override
    public boolean repactSc(long userId,int item_id) {
        Sc sc = mapper.findItemForRepeat(userId,item_id);
        if (sc != null){
            return true;
        }
        return false;
    }

    @Override
    public Sc findOneById(int item_id) {
        return mapper.findOneById(item_id);
    }


}
