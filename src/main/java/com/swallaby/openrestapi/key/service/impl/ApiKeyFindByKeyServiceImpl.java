package com.swallaby.openrestapi.key.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.swallaby.openrestapi.config.EhacheConfig;
import com.swallaby.openrestapi.key.repository.OpenApiKeyRepositorySupport;
import com.swallaby.openrestapi.key.service.ApiKeyFindByKeyService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApiKeyFindByKeyServiceImpl implements ApiKeyFindByKeyService {

    private final OpenApiKeyRepositorySupport openApiKeyRepository;

    @Override
    @Cacheable(cacheNames = EhacheConfig.API_KEYS, key = "#apiKey", unless = "#result == null")
    public String apiKeyFindByKey(String apiKey) {

        return openApiKeyRepository.findByKey(apiKey);
    }

}
