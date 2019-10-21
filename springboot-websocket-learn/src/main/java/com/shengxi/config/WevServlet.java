package com.shengxi.config;

import com.shengxi.SpringbootWebsocketLearnApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yan
 * @date 2019.10.21
 */
@Configuration
public class WevServlet extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootWebsocketLearnApplication.class);
    }

    /**
     * lambda表达式实现匿名内部类
     *
     * @return new WebServerFactoryCustomizer
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return webServerFactoryCustomizer -> webServerFactoryCustomizer.setPort(8081);
    }
}
