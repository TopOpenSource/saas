package com.sdstc.dynamicds.start.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "datasources.master")
public class MasterProperties {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private String type;
}
