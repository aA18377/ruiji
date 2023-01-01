package com.fff.utils;

/**
 * @Auther: zhaoss
 * @Date: 2022/12/8 - 12 - 08 - 16:01
 * @Description: com.fff.utils
 * @version: 1.0
 */
public class MyRepeatException extends Exception{
    public MyRepeatException() {
        super();
    }

    /**
     * 检查查询到的登录用户是否重复
     * @param message
     */
    public MyRepeatException(String message) {
        super(message);
    }
}
