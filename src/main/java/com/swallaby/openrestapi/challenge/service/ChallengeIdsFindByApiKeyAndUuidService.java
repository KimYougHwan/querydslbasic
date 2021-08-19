package com.swallaby.openrestapi.challenge.service;

import java.util.List;
import com.swallaby.openrestapi.common.ResponseDto;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface ChallengeIdsFindByApiKeyAndUuidService {
    public Mono<ResponseDto<List<String>>> findChallengeIdsByApiKeyAndUuid(String apiKey,
            String uuid);
}
