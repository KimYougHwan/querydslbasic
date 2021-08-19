package com.swallaby.openrestapi.challenge.service;

import java.util.List;
import com.swallaby.openrestapi.common.ResponseDto;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface ChallengesFindByApiKeyService {

    Mono<ResponseDto<List<String>>> findByApiKey(String apiKey);
}
