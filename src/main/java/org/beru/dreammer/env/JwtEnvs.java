package org.beru.dreammer.env;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties
public class JwtEnvs {
    private String jwtSecret;
    private String jwtIssuer;
    private String jwtExpiration;
}
