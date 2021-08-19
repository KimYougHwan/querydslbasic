package com.swallaby.openrestapi.config;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@Component
@RequiredArgsConstructor
public class DataSourceConfig {

    private final Environment env;

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource datasource() {
        log.info("datasource 확인 작업 : {}", (Object) env.getActiveProfiles());
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

}
