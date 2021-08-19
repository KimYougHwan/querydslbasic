package com.swallaby.openrestapi.challenge.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.swallaby.openrestapi.challenge.repository.ChallengeRepositorySupoort;
import com.swallaby.openrestapi.challenge.service.ChallengeIdsFindByApiKeyAndUuidService;
import com.swallaby.openrestapi.common.ResponseDto;
import com.swallaby.openrestapi.common.utils.WalkonStringUtils;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ChallengeIdsFindByApiKeyAndUuidServiceImpl
        implements ChallengeIdsFindByApiKeyAndUuidService {

    private final ChallengeRepositorySupoort challengeRepositorySupoort;

    @Override
    public Mono<ResponseDto<List<String>>> findChallengeIdsByApiKeyAndUuid(String apiKey,
            String uuid) {

        uuid = WalkonStringUtils.convertUuid(uuid);
        byte[] uuidByte = WalkonStringUtils.hexStringToByteArray(uuid);


        return challengeRepositorySupoort.findChallengesFindByApiKeyAndUuid(apiKey, uuidByte);
    }

}
