package com.github.onebot.core;

import com.github.onebot.config.OneBotConfig;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BotFactory {

    private final ApiHandler apiHandler;
    private final OneBotConfig oneBotConfig;

    @Autowired
    public BotFactory(ApiHandler apiHandler, OneBotConfig oneBotConfig) {
        this.apiHandler = apiHandler;
        this.oneBotConfig = oneBotConfig;
    }

    public Bot createBot(Long selfId, Session session) {

        return new Bot(selfId, session, apiHandler, oneBotConfig);
    }
}
