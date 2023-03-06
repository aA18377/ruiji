package com.yanmou.mybatisplusstudy.controller.backendController;

import com.yanmou.mybatisplusstudy.common.R;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * @Auther: zhaoss
 * @Date: 2023/2/27 - 02 - 27 - 13:02
 * @Description: com.yanmou.mybatisplusstudy.controller
 * @version: 1.0
 */
@Controller
@Slf4j
@RequestMapping("/common")
public class CommonController {
    @Value("${ruiji.basePath}")
    private String basePath;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @ResponseBody
    @PostMapping("/upload")
    public R<String> upload(@RequestBody MultipartFile file) {
        File uploadDir = new File(basePath);
        if (!uploadDir.exists()){
            uploadDir.mkdirs();
        }
        UUID uuid = UUID.randomUUID();
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String uploadPath = basePath + uuid + suffix;
        try {
            file.transferTo(new File(uploadPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileName = uuid +suffix;
        log.info("上传路径:"+uploadPath);
        return R.success(fileName);
    }


    /**
     * 文件下载
     */
    @ResponseBody
    @GetMapping("/download")
    public void download(String name,HttpServletResponse response){
        String downloadPath = basePath+name;
        File downloadFile = new File(downloadPath);
        int len = 0;
        byte[] bytes = new byte[1024 * 1024];
        response.setContentType("image/png");
        try {
            InputStream inputStream = new FileInputStream(downloadFile);
            ServletOutputStream outputStream = response.getOutputStream();
            while ((len = inputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
