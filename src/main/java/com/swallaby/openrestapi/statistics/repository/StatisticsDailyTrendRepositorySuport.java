package com.swallaby.openrestapi.statistics.repository;

import java.sql.Timestamp;
import com.swallaby.openrestapi.common.ResponseDto;
import com.swallaby.openrestapi.statistics.dto.DailyTrendDto;
import reactor.core.publisher.Mono;

public interface StatisticsDailyTrendRepositorySuport {

    public Mono<ResponseDto<DailyTrendDto>> findByApiKeyAndUuidAndDate(String apiKey, String uuid,
            Timestamp date);

}
