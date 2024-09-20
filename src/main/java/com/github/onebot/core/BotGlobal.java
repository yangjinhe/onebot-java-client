package com.github.onebot.core;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BotGlobal {
    public static final Map<Long, Bot> robots = new ConcurrentHashMap<>(32);


}
