package com.yanmou.mybatisplusstudy.utils;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import org.junit.jupiter.api.Test;

/**
 * @Auther: zhaoss
 * @Date: 2023/3/3 - 03 - 03 - 19:11
 * @Description: com.yanmou.mybatisplusstudy.utils
 * @version: 1.0
 */
public class CodeUtil {
    public static String nextId(Integer length) {
        if (length > 19){
            return "请将长度控制在19位之nei";
        }
        String aLong = new DefaultIdentifierGenerator().nextId(new Object()).toString();
        return aLong.substring(aLong.length()-length);
    }
}
