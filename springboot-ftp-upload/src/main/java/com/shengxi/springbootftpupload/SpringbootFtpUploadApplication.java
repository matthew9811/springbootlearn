package com.shengxi.springbootftpupload;

import javax.servlet.MultipartConfigElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
public class SpringbootFtpUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootFtpUploadApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(74L));
        factory.setMaxRequestSize(DataSize.ofMegabytes(250L));
        return factory.createMultipartConfig();
    }

}
