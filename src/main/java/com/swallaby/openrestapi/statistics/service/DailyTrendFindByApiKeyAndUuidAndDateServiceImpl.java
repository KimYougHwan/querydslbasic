package com.swallaby.openrestapi.statistics.service;

import java.sql.Timestamp;
import org.springframework.stereotype.Service;
import com.swallaby.openrestapi.statistics.dto.DailyTrendDto;
import com.swallaby.openrestapi.statistics.service.impl.DailyTrendFindByApiKeyAndUuidAndDateService;
import reactor.core.publisher.Mono;

@Service
public class DailyTrendFindByApiKeyAndUuidAndDateServiceImpl
        implements DailyTrendFindByApiKeyAndUuidAndDateService {

    @Override
    public Mono<DailyTrendDto> findByApiKeyAndUuidAndDate(String apiKey, String uuid,
            Timestamp date) {

        return null;
    }

}
