package org.beru.dreammer.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("storage")
@Getter
@Setter
public class StorageConfig {
    private String location;
    public StorageConfig() {
        this.location = "your_project_dirs";
    }
}
