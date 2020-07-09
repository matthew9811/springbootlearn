package com.shengxi.springbootftpupload.conf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yan
 * @version 1.0.0
 * @date 2019-12-11 10:41:41
 */
@Data
@ToString
@EqualsAndHashCode
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.ftp")
public class FtpProperties {
    private String host;
    private String port;
    private String username;
    private String password;
    private String basePath;
    private String httpPath;
}
