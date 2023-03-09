package com.yanmou.mybatisplusstudy.controller.backendController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: zhaoss
 * @Date: 2023/2/24 - 02 - 24 - 11:23
 * @Description: com.yanmou.mybatisplusstudy.controller
 * @version: 1.0
 */
@Controller
@Slf4j
public class BackendLoginController {
    /**
     * 后台主页
     */
    @GetMapping("/index.html")
    public String index(){
        return "backend/index";
    }

    /**
     *解决后台主页嵌套html无法外部访问的问题
     */
    @GetMapping("/page/**")
    public String defind(HttpServletRequest request){
        String requestURL = request.getRequestURL().toString();
        String substring = requestURL.substring(26,requestURL.length()-5);
        return  "backend/page"+substring;
    }
}
