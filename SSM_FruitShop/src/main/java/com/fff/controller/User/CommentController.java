package com.fff.controller.User;

import com.fff.pojo.Comment;
import com.fff.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/27 - 12 - 27 - 12:26
 * @Description: com.fff.controller.User
 * @version: 1.0
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService service;

    /**
     * 执行订单评价
     */
    @RequestMapping("/exAdd")
    public String exAdd(Comment comment, Model model) {
        long l = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(l);
        comment.setAddTime(timestamp);
        service.insertByEntry(comment);
        return "redirect:/itemOrder/my";
    }


}
