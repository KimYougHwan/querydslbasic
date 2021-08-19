package com.swallaby.openrestapi.challenge.repository;

import java.util.List;
import com.swallaby.openrestapi.challenge.dto.ChallengeInformationDto;
import com.swallaby.openrestapi.common.ResponseDto;
import reactor.core.publisher.Mono;

public interface ChallengeRepositorySupoort {

    public Mono<ResponseDto<List<String>>> findByApiKey(String apiKey);

    public Mono<ResponseDto<String>> findByApiKeyAndPhoneNumber(String apiKey, String phoneNumber);

    public Mono<ResponseDto<ChallengeInformationDto>> findByApiKeyAndChallengeId(String apiKey,
            byte[] challengeId);

    public Mono<ResponseDto<Boolean>> findByChallengeIdAndUuid(byte[] challengeId, byte[] uuid);

    public Mono<ResponseDto<Integer>> findChallengeTotalStepsByApiKeyAndChallengeIdAndUuid(
            byte[] challengeId, byte[] uuid);

    public Mono<ResponseDto<List<String>>> findChallengesFindByApiKeyAndUuid(String apiKey,
            byte[] uuid);
}
