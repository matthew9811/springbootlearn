package com.shengxi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author yan
 * 快速访问
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/ws").setViewName("/ws");
    }
}
