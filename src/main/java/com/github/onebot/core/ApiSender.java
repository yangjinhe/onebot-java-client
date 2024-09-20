package com.github.onebot.core;

import com.alibaba.fastjson.JSONObject;
import jakarta.websocket.Session;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

class ApiSender extends Thread {
    private final Session apiSession;
    private final Long apiTimeout;
    private JSONObject responseJSON;

    ApiSender(Session apiSession, Long apiTimeout) {
        this.apiSession = apiSession;
        this.apiTimeout = apiTimeout;
    }

    JSONObject sendApiJson(JSONObject apiJSON) throws IOException, InterruptedException {
        synchronized (apiSession) {
            apiSession.getBasicRemote().sendText(apiJSON.toString());
        }
        synchronized (this) {
            this.wait(apiTimeout);
        }
        return responseJSON;
    }


    void onReceiveJson(JSONObject responseJSON) {
        this.responseJSON = responseJSON;
        synchronized (this) {
            this.notify();
        }
    }

}
