package com.swallaby.openrestapi.statistics.service.impl;

import java.sql.Timestamp;
import com.swallaby.openrestapi.statistics.dto.DailyTrendDto;
import reactor.core.publisher.Mono;

public interface DailyTrendFindByApiKeyAndUuidAndDateService {

    public Mono<DailyTrendDto> findByApiKeyAndUuidAndDate(String apiKey, String uuid,
            Timestamp date);
}
