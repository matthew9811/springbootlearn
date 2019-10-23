package com.shengxi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @author yan
 * 启动接口
 */
@EnableWebSocket
@SpringBootApplication
public class SpringbootWebsocketLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebsocketLearnApplication.class, args);
    }

}
