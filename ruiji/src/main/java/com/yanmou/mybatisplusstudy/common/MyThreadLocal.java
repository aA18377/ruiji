package com.yanmou.mybatisplusstudy.common;

/**
 * @Auther: zhaoss
 * @Date: 2023/2/26 - 02 - 26 - 0:43
 * @Description: com.yanmou.mybatisplusstudy.common
 * @version: 1.0
 */
public class MyThreadLocal {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public static void setThreadLocal(long id){
        threadLocal.set(id);
    }
    public static long get(){
        return threadLocal.get();
    }
}
