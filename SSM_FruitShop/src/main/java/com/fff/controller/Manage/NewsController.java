package com.fff.controller.Manage;

import com.fff.pojo.News;
import com.fff.service.news.NewsService;
import com.fff.service.news.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/17 - 12 - 17 - 20:47
 * @Description: com.fff.controller
 * @version: 1.0
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService service;

    /**
     * 列表分页
     * @param model
     * @param inputPage
     * @param name
     * @return
     */
    @RequestMapping("/findBySql")
    public String newsPage(Model model,String inputPage,String name){
        int pageSize = 10;
        Map<String, Object> map = service.listDetailPage(inputPage, pageSize, name);
        model.addAttribute("datas",map);
        return "news/news";
    }

    /**
     * 添加视图
     * @return
     */
    @RequestMapping("/add")
    public String addPage(){
        return "news/add";
    }

    /**
     * 执行添加
     * @param news
     * @return
     */
    @RequestMapping("/exAdd")
    public String add(News news){
        long time = System.currentTimeMillis();
        news.setAddTime(new Timestamp(time));
        service.insertByEntry(news);
        return "redirect:/news/findBySql";
    }

    /**
     * 执行删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public String delete(int id){
        service.deleteById(id);
        return "redirect:/news/findBySql";
    }

    /**
     * 修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/update")
    public String updatePage(int id,Model model){
        News obj = service.findOneById(id);
        model.addAttribute("obj",obj);
        return "news/update";
    }

    /**
     * 执行修改
     */
    @RequestMapping("/exUpdate")
    public String update(News news){
        long time = System.currentTimeMillis();
        news.setAddTime(new Timestamp(time));
        service.updateByEntry(news);
        return "redirect:/news/findBySql";
    }
}
