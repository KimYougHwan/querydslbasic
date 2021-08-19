package com.swallaby.openrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class WalkonOpenRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalkonOpenRestApiApplication.class, args);
    }

}
