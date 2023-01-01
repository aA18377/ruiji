package com.fff.controller.User;

import com.alibaba.fastjson.JSONObject;
import com.fff.pojo.Message;
import com.fff.service.message.MessageService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/21 - 12 - 21 - 21:55
 * @Description: com.fff.controller.User
 * @version: 1.0
 */
@Controller
@RequestMapping("/message")
public class uMessageController {
    @Autowired
    private MessageService service;
    /**
     * 跳转留言界面
     */
    @RequestMapping("/add")
    public String addPage(){
        return "/message/add";
    }

    /**
     * 新增留言功能
     */
    @ResponseBody
    @RequestMapping("/exAdd.do")
    public String addMessage(Message message){
        service.insertByEntry(message);
        String rusult = "11等级";
        Map<String,Object> map = new HashMap<>(1);
        map.put("result",rusult);
        return JSONObject.toJSONString(map);
    }

    @Test
    public void test(){
        Map<String,Object> map = new HashMap<>(2);
        map.put("第一个","a");
        map.put("第二个","B");
        System.out.println(JSONObject.toJSONString(map));
    }


}
