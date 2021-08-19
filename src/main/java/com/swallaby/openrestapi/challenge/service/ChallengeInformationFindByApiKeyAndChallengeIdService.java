package com.swallaby.openrestapi.challenge.service;

import com.swallaby.openrestapi.challenge.dto.ChallengeInformationDto;
import com.swallaby.openrestapi.common.ResponseDto;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface ChallengeInformationFindByApiKeyAndChallengeIdService {

    public Mono<ResponseDto<ChallengeInformationDto>> findByApiKeyAndChallengeId(String apiKey,
            String challengeId);
}
