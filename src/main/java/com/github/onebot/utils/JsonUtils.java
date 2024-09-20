package com.github.onebot.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

public class JsonUtils {

    /**
     * 将对象转换为 JSON 字符串。
     *
     * @param obj 对象
     * @return JSON 字符串
     */
    public static String toJsonString(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * 将 JSON 字符串转换为指定类型的对象。
     *
     * @param jsonString JSON 字符串
     * @param clazz      类型
     * @param <T>        泛型标记
     * @return 转换后的对象
     */
    public static <T> T parseObject(String jsonString, Class<T> clazz) {
        return JSON.parseObject(jsonString, clazz);
    }

    /**
     * 将 JSON 字符串转换为 Map 对象。
     *
     * @param jsonString JSON 字符串
     * @return Map 对象
     */
    public static Map<String, Object> toMap(String jsonString) {
        return JSON.parseObject(jsonString, new TypeReference<Map<String, Object>>() {
        });
    }

    /**
     * 将 JSON 字符串转换为 List 对象。
     *
     * @param jsonString JSON 字符串
     * @param clazz      列表元素类型
     * @param <T>        泛型标记
     * @return List 对象
     */
    public static <T> List<T> toList(String jsonString, Class<T> clazz) {
        return JSON.parseArray(jsonString, clazz);
    }

    /**
     * 将 JSON 字符串转换为 JSONObject 对象。
     *
     * @param jsonString JSON 字符串
     * @return JSONObject 对象
     */
    public static JSONObject toJSONObject(String jsonString) {
        return JSON.parseObject(jsonString);
    }

    /**
     * 将 JSON 字符串转换为指定类型的复杂对象。
     *
     * @param jsonString    JSON 字符串
     * @param typeReference 类型
     * @param <T>           泛型标记
     * @return 转换后的对象
     */
    public static <T> T parseObject(String jsonString, TypeReference<T> typeReference) {
        return JSON.parseObject(jsonString, typeReference);
    }
}

