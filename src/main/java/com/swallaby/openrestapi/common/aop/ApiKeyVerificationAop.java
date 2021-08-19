package com.swallaby.openrestapi.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import com.swallaby.openrestapi.common.enums.ResponseFailEnum;
import com.swallaby.openrestapi.common.exception.WalkonRestApiException;
import com.swallaby.openrestapi.key.service.ApiKeyFindByKeyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public class ApiKeyVerificationAop {

    private final ApiKeyFindByKeyService apiKeyFindByKeyService;

    @Before("within(@org.springframework.web.bind.annotation.RestController *)")
    public void apiKeyVerification(JoinPoint joinPoint) throws InterruptedException {

        log.debug("start aop ============");

        CodeSignature params = (CodeSignature) joinPoint.getSignature();

        if (params != null && "apiKey".equals(params.getParameterNames()[0])) {
            String apiKey = String.valueOf(joinPoint.getArgs()[0]);

            if (apiKey == null || apiKey.isBlank()) {

                throw new WalkonRestApiException(ResponseFailEnum.NOT_AUTHORIZED);
            } else {
                createException(apiKeyFindByKeyService.apiKeyFindByKey(apiKey));

            }
        } else {
            throw new WalkonRestApiException(ResponseFailEnum.NOT_AUTHORIZED);
        }

        log.debug("end aop ==============");
    }

    private void createException(String apiKey) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new WalkonRestApiException(ResponseFailEnum.NOT_AUTHORIZED);
        }
    }

}
