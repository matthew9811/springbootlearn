package com.shengxi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 实现springboot1.x版本的websocket整合
 *
 * @author yan
 */
@SpringBootApplication
public class SpringbootWebsocket2Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringbootWebsocket2Application.class, args);
    }
}
