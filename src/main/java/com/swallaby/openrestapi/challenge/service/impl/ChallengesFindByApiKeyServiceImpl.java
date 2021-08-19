package com.swallaby.openrestapi.challenge.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.swallaby.openrestapi.challenge.repository.ChallengeRepositorySupoort;
import com.swallaby.openrestapi.challenge.service.ChallengesFindByApiKeyService;
import com.swallaby.openrestapi.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ChallengesFindByApiKeyServiceImpl implements ChallengesFindByApiKeyService {

    private final ChallengeRepositorySupoort challengeRepositorySupoort;

    @Override
    public Mono<ResponseDto<List<String>>> findByApiKey(String apiKey) {
        return challengeRepositorySupoort.findByApiKey(apiKey);
    }

}
