package com.swallaby.openrestapi.challenge.service.impl;

import org.springframework.stereotype.Service;
import com.swallaby.openrestapi.challenge.dto.ChallengeInformationDto;
import com.swallaby.openrestapi.challenge.repository.ChallengeRepositorySupoort;
import com.swallaby.openrestapi.challenge.service.ChallengeInformationFindByApiKeyAndChallengeIdService;
import com.swallaby.openrestapi.common.ResponseDto;
import com.swallaby.openrestapi.common.utils.WalkonStringUtils;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ChallengeInformationFindByApiKeyAndChallengeIdServiceImpl
        implements ChallengeInformationFindByApiKeyAndChallengeIdService {

    private final ChallengeRepositorySupoort challengeRepositorySupoort;

    @Override
    public Mono<ResponseDto<ChallengeInformationDto>> findByApiKeyAndChallengeId(String apiKey,
            String challengeId) {


        byte[] challengeIdUnHex = WalkonStringUtils.hexStringToByteArray(challengeId);

        return challengeRepositorySupoort.findByApiKeyAndChallengeId(apiKey, challengeIdUnHex);
    }
}
