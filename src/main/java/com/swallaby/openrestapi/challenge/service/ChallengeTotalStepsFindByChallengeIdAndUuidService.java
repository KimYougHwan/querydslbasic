package com.swallaby.openrestapi.challenge.service;

import com.swallaby.openrestapi.common.ResponseDto;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface ChallengeTotalStepsFindByChallengeIdAndUuidService {
    public Mono<ResponseDto<Integer>> findByChallengeIdAndUuid(String challengeId, String uuid);
}
