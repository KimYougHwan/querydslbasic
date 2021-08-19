package com.swallaby.openrestapi.statistics.controller;

import java.sql.Timestamp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.swallaby.openrestapi.common.ResponseDto;
import com.swallaby.openrestapi.statistics.dto.DailyTrendDto;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class StatisticsDailyTrendRestController {

    @GetMapping("/statistics/daily-trend/{apiKey}/{uuid}/{date}")
    public Mono<ResponseDto<DailyTrendDto>> findByApiKeyAndUuidAndDate(
            @PathVariable(value = "apiKey") String apiKey,
            @PathVariable(value = "uuid") String uuid,
            @PathVariable(value = "date") Timestamp date) {

        return null;
    }
}
