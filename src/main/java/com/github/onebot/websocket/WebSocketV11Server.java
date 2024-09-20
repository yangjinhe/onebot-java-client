package com.github.onebot.websocket;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.onebot.config.OneBotThreadConfig;
import com.github.onebot.config.WebSocketConfig;
import com.github.onebot.core.ApiHandler;
import com.github.onebot.core.Bot;
import com.github.onebot.core.BotFactory;
import com.github.onebot.core.BotGlobal;
import com.github.onebot.core.EventHandler;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@ServerEndpoint(value = "/v11", configurator = WebSocketConfig.class)
@Component
public class WebSocketV11Server {

    private static ApplicationContext applicationContext;

    private static BotFactory botFactory;
    private static ApiHandler apiHandler;
    private static EventHandler eventHandler;
    private static ExecutorService executor;

    /**
     * 连接uid和连接会话
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketV11Server.applicationContext = applicationContext;
        botFactory = applicationContext.getBean(BotFactory.class);
        apiHandler = applicationContext.getBean(ApiHandler.class);
        eventHandler = applicationContext.getBean(EventHandler.class);
        OneBotThreadConfig oneBotThreadConfig = applicationContext.getBean(OneBotThreadConfig.class);
        executor = new ThreadPoolExecutor(oneBotThreadConfig.getCorePoolSize(),
                oneBotThreadConfig.getMaxPoolSize(),
                oneBotThreadConfig.getKeepAliveTime(),
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(oneBotThreadConfig.getWorkQueueSize()));
    }

    /*public static void sendMessage(String message, String userId) {
        WebSocketV11Server webSocket = BotGlobal.robots.get(userId);
        if (webSocket != null) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
                log.info("【websocket消息】发送消息成功,用户id=" + userId + ",消息内容：" + message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

    public static String getHeader(Session session, String headerName) {
        final HandshakeRequest request = (HandshakeRequest) session.getUserProperties().get("request");
        String headerVal = null;
        for (Map.Entry<String, List<String>> entry : request.getHeaders().entrySet()) {
//            log.info(entry.getKey() + ":" + entry.getValue());
            if (entry.getKey().equals(headerName)) {
                headerVal = Convert.toStr(entry.getValue().get(0));
            }
        }
        if (StrUtil.isBlank(headerVal)) {
            log.error("获取header失败，不安全的链接，即将关闭");
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return headerVal;
    }

    @OnOpen
    public void onOpen(Session session) {
        Long selfId = Convert.toLong(getHeader(session, "X-Self-ID"));
        if (selfId == null) {
            log.warn("【websocket消息】缺少必要的X-Self-ID参数");
            return;
        }

        Bot bot = botFactory.createBot(selfId, session);
        BotGlobal.robots.put(selfId, bot);
        log.info("【websocket消息】有新的连接,连接id=" + selfId + ":" + this);
    }

    @OnClose
    public void onClose(Session session) {
        Long selfId = Convert.toLong(getHeader(session, "X-Self-ID"));
        if (selfId == null) {
            log.warn("【websocket消息】缺少必要的X-Self-ID参数");
            return;
        }
        BotGlobal.robots.remove(selfId);
        log.info("【websocket消息】连接断开:" + selfId);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        Long selfId = Convert.toLong(getHeader(session, "X-Self-ID"));
        log.info("【websocket消息】WebSocket发生错误 {}，错误信息为：{}", selfId, error.getMessage());
        error.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("收到消息：" + message);
        Long selfId = Convert.toLong(getHeader(session, "X-Self-ID"));
        if (selfId == null) {
            log.warn("【websocket消息】缺少必要的X-Self-ID参数");
            return;
        }
        Bot bot = BotGlobal.robots.get(selfId);
        if (null == bot) {
            bot = botFactory.createBot(selfId, session);
            BotGlobal.robots.put(selfId, bot);
        }
        bot.setSession(session);
        JSONObject msgObj = JSON.parseObject(message);
        if (msgObj.containsKey("echo")) {
            apiHandler.onReceiveApiMessage(msgObj);
        } else {
            Bot finalBot = bot;
            executor.submit(() -> eventHandler.handle(finalBot, msgObj));
        }
    }


}
