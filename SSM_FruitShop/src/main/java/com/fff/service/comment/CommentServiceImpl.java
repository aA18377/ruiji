package com.fff.service.comment;

import com.fff.mapper.comment.CommentMapper;
import com.fff.pojo.Comment;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/27 - 12 - 27 - 12:57
 * @Description: com.fff.service
 * @version: 1.0
 */
public class CommentServiceImpl extends SqlSessionDaoSupport implements CommentService {
    @Autowired
    private CommentMapper mapper;
    @Override
    public void insertByEntry(Comment comment) {
        mapper.insertByEntry(comment);
    }
}
