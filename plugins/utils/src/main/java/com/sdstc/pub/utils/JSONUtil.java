package com.sdstc.pub.utils;

import com.alibaba.fastjson.JSON;

public class JSONUtil {
    public static String parseObject(Object obj){
        return JSON.toJSONString(obj);
    }
}
