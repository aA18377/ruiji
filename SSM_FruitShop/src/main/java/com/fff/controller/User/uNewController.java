package com.fff.controller.User;

import com.fff.pojo.News;
import com.fff.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/21 - 12 - 21 - 21:32
 * @Description: com.fff.controller.User
 * @version: 1.0
 */
@Controller
@RequestMapping("/news")
public class uNewController {
    @Autowired
    private NewsService service;
    /**
     * 跳转公共页面
     */
    @RequestMapping("/list")
    public String newsList(Model model,String inputPage){
        int pageSize = 10;
        String sql = "select * from news";
        Map<String, Object> datas = service.pages(inputPage, pageSize, sql);
        model.addAttribute("datas",datas);
        return "news/list";
    }

    /**
     * 查看具体公告内容
     */
    @RequestMapping("/view")
    public String newsView(int id){
        News news = service.findOneById(id);
        return "/news/view";
    }
}
