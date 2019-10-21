package com.shengxi.config;

import com.shengxi.SpringbootWebsocketLearnApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
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
}
