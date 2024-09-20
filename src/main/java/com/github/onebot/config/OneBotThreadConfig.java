package com.github.onebot.config;

import com.github.onebot.core.BotPlugin;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "onebot.thread")
public class OneBotThreadConfig {

    private Integer corePoolSize=5;
    private Integer maxPoolSize=20;
    private Integer keepAliveTime=2000;
    private Integer workQueueSize=512;

}
