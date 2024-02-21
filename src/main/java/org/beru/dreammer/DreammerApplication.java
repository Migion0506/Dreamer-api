package org.beru.dreammer;

import org.beru.dreammer.web.config.StorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
@EnableConfigurationProperties(StorageConfig.class)
public class DreammerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreammerApplication.class, args);
	}

}
