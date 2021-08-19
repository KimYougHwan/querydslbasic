package com.swallaby.openrestapi.challenge.service;

import com.swallaby.openrestapi.common.ResponseDto;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface UserIdFindByApiKeyAndPhoneNumberService {

    public Mono<ResponseDto<String>> userIdFindByApiKeyAndPhoneNumber(String apiKey,
            String phoneNumber);
}
