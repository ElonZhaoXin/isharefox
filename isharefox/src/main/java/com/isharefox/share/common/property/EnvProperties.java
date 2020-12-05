package com.isharefox.share.common.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("env")
@Data
public class EnvProperties {
    private String domain;
}
