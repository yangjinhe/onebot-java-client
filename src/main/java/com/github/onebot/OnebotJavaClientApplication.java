package com.github.onebot;

import com.github.onebot.websocket.WebSocketV11Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class OnebotJavaClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(OnebotJavaClientApplication.class, args);
        WebSocketV11Server.setApplicationContext(applicationContext);
        log.info("启动成功");
    }

}
