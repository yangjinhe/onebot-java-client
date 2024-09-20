package com.github.onebot.core;

import com.alibaba.fastjson.JSONObject;
import com.github.onebot.config.OneBotConfig;
import jakarta.websocket.Session;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * API处理类
 */
@Component
public class ApiHandler {
    OneBotConfig oneBotConfig;
    private long apiEcho = 0;//用于标记是哪次发送api，接受时作为key放入apiResponseMap
    private final Map<String, ApiSender> apiCallbackMap = new HashMap<>();//用于存放api调用，收到响应时put，处理完成remove

    public ApiHandler(OneBotConfig oneBotConfig) {
        this.oneBotConfig = oneBotConfig;
    }

    /**
     * 收到 以前调用的API 的响应
     *
     * @param message 内容
     */
    public void onReceiveApiMessage(JSONObject message) {
        String echo = message.get("echo").toString();
        ApiSender apiSender = apiCallbackMap.get(echo);
        apiSender.onReceiveJson(message);
        apiCallbackMap.remove(echo);
    }


    /**
     * 构造API需要的json，使用预定义的Enum
     *
     * @param action 需要调用的API
     * @param params 参数
     * @return 结果
     */
    private JSONObject constructApiJSON(ApiEnum action, JSONObject params) {
        JSONObject apiJSON = new JSONObject();
        apiJSON.put("action", action.getCode());
        if (params != null)
            apiJSON.put("params", params);
        apiJSON.put("echo", apiEcho++);

        return apiJSON;
    }

    /**
     * 调用定义好的API
     *
     * @param botSession 机器人session
     * @param action     执行的操作
     * @param params     参数
     * @return 结果
     */
    public JSONObject sendApiMessage(Session botSession, ApiEnum action, JSONObject params) {
        JSONObject apiJSON = constructApiJSON(action, params);
        String echo = apiJSON.getString("echo");
        ApiSender apiSender = new ApiSender(botSession, oneBotConfig.getTimeout());
        apiCallbackMap.put(echo, apiSender);
        JSONObject retJson;
        try {
            retJson = apiSender.sendApiJson(apiJSON);
        } catch (Exception e) {
            e.printStackTrace();
            retJson = new JSONObject();
            retJson.put("status", "failed");
            retJson.put("retcode", -1);
        }
        return retJson;
    }


    /**
     * 构造API需要的json，自定义request
     *
     * @param apiRequest 自定义请求
     * @return 结果
     */
    private JSONObject constructApiJSON(IApiRequest apiRequest) {
        JSONObject apiJSON = new JSONObject();
        apiJSON.put("action", apiRequest.getUrl());
        if (apiRequest.getParams() != null)
            apiJSON.put("params", apiRequest.getParams());
        apiJSON.put("echo", apiEcho++);
        return apiJSON;
    }

    /**
     * 发送自定义API
     *
     * @param botSession Session
     * @param apiRequest 自定义请求
     * @return 结果
     * @throws IOException          发送异常
     * @throws InterruptedException 线程异常
     */
    @SuppressWarnings("unused")
    public JSONObject sendApiMessage(Session botSession, IApiRequest apiRequest) throws IOException, InterruptedException {
        JSONObject apiJSON = constructApiJSON(apiRequest);
        String echo = apiJSON.getString("echo");
        ApiSender apiSender = new ApiSender(botSession, oneBotConfig.getTimeout());
        apiCallbackMap.put(echo, apiSender);
        JSONObject retJson;
        retJson = apiSender.sendApiJson(apiJSON);
        return retJson;
    }
}
