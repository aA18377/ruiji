package com.fff.controller.Manage;

import com.fff.service.message.MessageService;
import com.fff.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/17 - 12 - 17 - 21:59
 * @Description: com.fff.controller
 * @version: 1.0
 */
@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService service;

    @RequestMapping("findBySql")
    public String newsPage(Model model, String inputPage, String name){
        int pageSize = 10;
        Map<String, Object> map = service.listDetailPage(inputPage, pageSize, name);
        model.addAttribute("datas",map);
        return "message/message";
    }

}
