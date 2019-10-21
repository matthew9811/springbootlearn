package com.shengxi.config;

import com.shengxi.SpringbootWebsocketLearnApplication;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
public class WevServlet extends SpringBootServletInitializer implements ApplicationRunner {

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


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("This will be execute when the project was started!");
    }
}
