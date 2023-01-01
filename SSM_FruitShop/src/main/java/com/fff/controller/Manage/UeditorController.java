package com.fff.controller.Manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/18 - 12 - 18 - 14:09
 * @Description: com.fff.controller
 * @version: 1.0
 */
@Controller
@RequestMapping("/ueditor")
public class UeditorController {


    @RequestMapping("/saveFile")
    @ResponseBody
    public Map<String, Object> vimUpload(@RequestParam(value = "upfile", required = false) MultipartFile file, HttpServletRequest req) {
        Map<String, Object> params = new HashMap<>();
        String n = UUID.randomUUID().toString();
        String path = req.getSession().getServletContext().getRealPath("\\")+"\\reourcesHtml\\ueditor\\upload\\" + n + file.getOriginalFilename();
        File newFile = new File(path);
        //通过CommonsMultipartFile的方法直接写文件
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String visitUrl = "/resource/ueditor/upload/" + n + file.getOriginalFilename();
        params.put("state", "SUCCESS");
        params.put("url", visitUrl);
        params.put("size", file.getSize());
        params.put("original", file.getOriginalFilename());
        params.put("type", file.getContentType());
        return params;
    }
}

