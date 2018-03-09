package com.server.producer.utils;

import com.google.gson.Gson;


/**
 * 项目名称:producer
 * 描述:
 * 创建人:ryw
 * 创建时间:2017/11/3
 */
public class JsonUtils {

    public static<T> String parseJsontoString(T obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static <T> T parseStringtoJson(String json,Class<T> clazz){
        Gson gson = new Gson();
        return gson.fromJson(json,clazz);
    }

}
