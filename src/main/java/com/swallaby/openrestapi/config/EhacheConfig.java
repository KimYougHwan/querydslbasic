package com.swallaby.openrestapi.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableCaching
@RequiredArgsConstructor
@Getter
public class EhacheConfig extends CachingConfigurerSupport {

    public static final String API_KEYS = "apiKeys";

}
