package com.swallaby.openrestapi.challenge.service.impl;

import org.springframework.stereotype.Service;
import com.swallaby.openrestapi.challenge.repository.ChallengeRepositorySupoort;
import com.swallaby.openrestapi.challenge.service.ChallengeTotalStepsFindByChallengeIdAndUuidService;
import com.swallaby.openrestapi.common.ResponseDto;
import com.swallaby.openrestapi.common.utils.WalkonStringUtils;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ChallengeTotalStepsFindByChallengeIdAndUuidServiceImpl
        implements ChallengeTotalStepsFindByChallengeIdAndUuidService {

    private final ChallengeRepositorySupoort challengeRepositorySupoort;

    @Override
    public Mono<ResponseDto<Integer>> findByChallengeIdAndUuid(String challengeId, String uuid) {

        uuid = WalkonStringUtils.convertUuid(uuid);
        byte[] uuidByte = WalkonStringUtils.hexStringToByteArray(uuid);
        byte[] challengeIdByte = WalkonStringUtils.hexStringToByteArray(challengeId);
        return challengeRepositorySupoort
                .findChallengeTotalStepsByApiKeyAndChallengeIdAndUuid(challengeIdByte, uuidByte);
    }
}
