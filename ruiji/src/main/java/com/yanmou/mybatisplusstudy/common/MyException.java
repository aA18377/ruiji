package com.yanmou.mybatisplusstudy.common;

import com.yanmou.mybatisplusstudy.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Auther: zhaoss
 * @Date: 2023/2/24 - 02 - 24 - 19:47
 * @Description: com.yanmou.mybatisplusstudy.config
 * @version: 1.0
 */
//控制器增强，annotations声明要增强哪种类型的控制器
@ControllerAdvice(annotations = {Controller.class, RestController.class})
//最终要返回结果集
@RestController
//打印报错日志
@Slf4j
public class MyException {
    //异常处理器，声明特定的异常类型
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R ExceptionHandler(SQLIntegrityConstraintViolationException exception){
        log.info(exception.getMessage());
        if (exception.getMessage().contains("Duplicate")) {
            //同种类型的异常，报错原因也不尽相同
            String[] exList = exception.getMessage().split(" ");
            return R.error("用户名已存在："+exList[2]);
        }
        return R.error("发生未知错误");
    }


}
