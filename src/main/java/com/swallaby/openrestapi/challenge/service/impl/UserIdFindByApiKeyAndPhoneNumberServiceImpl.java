package com.swallaby.openrestapi.challenge.service.impl;

import org.springframework.stereotype.Service;
import com.swallaby.openrestapi.challenge.repository.ChallengeRepositorySupoort;
import com.swallaby.openrestapi.challenge.service.UserIdFindByApiKeyAndPhoneNumberService;
import com.swallaby.openrestapi.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserIdFindByApiKeyAndPhoneNumberServiceImpl
        implements UserIdFindByApiKeyAndPhoneNumberService {

    private final ChallengeRepositorySupoort challengeRepositorySupoort;

    @Override
    public Mono<ResponseDto<String>> userIdFindByApiKeyAndPhoneNumber(String apiKey,
            String phoneNumber) {

        return challengeRepositorySupoort.findByApiKeyAndPhoneNumber(apiKey, phoneNumber);
    }

}
