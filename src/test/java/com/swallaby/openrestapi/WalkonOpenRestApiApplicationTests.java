package com.swallaby.openrestapi;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = "management.metrics.export.wavefront.enabled=false")
@SpringBootTest
class WalkonOpenRestApiApplicationTests {

    @Test
    @Disabled("초기 세팅!!")
    void contextLoads() {
        fail("");
    }


}
