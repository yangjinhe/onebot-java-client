package com.github.onebot.config;

import com.github.onebot.core.BotPlugin;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "onebot.config")
public class OneBotConfig {
    /**
     * 插件列表
     */
    List<Class<? extends BotPlugin>> pluginList = new ArrayList<>();

    /**
     * 授权码
     */
    private String authorization;

    /**
     * 超时时间
     */
    private Long timeout = 120000L;

}
