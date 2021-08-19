package com.swallaby.openrestapi.challenge.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.swallaby.openrestapi.challenge.dto.ChallengeInformationDto;
import com.swallaby.openrestapi.challenge.service.ChallengeIdsFindByApiKeyAndUuidService;
import com.swallaby.openrestapi.challenge.service.ChallengeInformationFindByApiKeyAndChallengeIdService;
import com.swallaby.openrestapi.challenge.service.ChallengeTotalStepsFindByChallengeIdAndUuidService;
import com.swallaby.openrestapi.challenge.service.ChallengesFindByApiKeyService;
import com.swallaby.openrestapi.challenge.service.IsEnableChallengeUserFindByChallengeIdAndUuidService;
import com.swallaby.openrestapi.challenge.service.UserIdFindByApiKeyAndPhoneNumberService;
import com.swallaby.openrestapi.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ChallengeRestController {

    private final ChallengesFindByApiKeyService challengesFindByApiKeyService;
    private final UserIdFindByApiKeyAndPhoneNumberService userIdFindByApiKeyAndPhoneNumberService;
    private final ChallengeInformationFindByApiKeyAndChallengeIdService challengeInformationFindByApiKeyAndChallengeIdService;
    private final IsEnableChallengeUserFindByChallengeIdAndUuidService isEnableChallengeUserFindByChallengeIdAndUuidService;
    private final ChallengeTotalStepsFindByChallengeIdAndUuidService challengeTotalStepsFindByChallengeIdAndUuidService;
    private final ChallengeIdsFindByApiKeyAndUuidService challengeIdsFindByApiKeyAndUuidService;

    @GetMapping("/challenges")
    public Mono<ResponseDto<List<String>>> findChallengesByApiKey(
            @RequestParam(value = "apiKey") String apiKey) {

        return challengesFindByApiKeyService.findByApiKey(apiKey);
    }

    @GetMapping("/challenge/user")
    public Mono<ResponseDto<String>> findChallengeUserIdByPhoneNumber(
            @RequestParam(value = "apiKey") String apiKey,
            @RequestParam(value = "phoneNumber") String phoneNumber) {

        phoneNumber = URLEncoder.encode(phoneNumber, StandardCharsets.UTF_8);

        return userIdFindByApiKeyAndPhoneNumberService.userIdFindByApiKeyAndPhoneNumber(apiKey,
                phoneNumber);
    }

    @GetMapping("/challenge")
    public Mono<ResponseDto<ChallengeInformationDto>> findChallengeInformationByApiKeyAndChallengeId(
            @RequestParam(value = "apiKey") String apiKey,
            @RequestParam(value = "challengeId") String challengeId) {

        return challengeInformationFindByApiKeyAndChallengeIdService
                .findByApiKeyAndChallengeId(apiKey, challengeId);
    }

    @GetMapping("/challenge/participation/status")
    public Mono<ResponseDto<Boolean>> findChallengeParticipationStatusByApiKeyAndChallengeIdAndUuid(
            @RequestParam(value = "apiKey") String apiKey,
            @RequestParam(value = "challengeId") String challengeId,
            @RequestParam(value = "uuid") String uuid) {

        return isEnableChallengeUserFindByChallengeIdAndUuidService
                .findByChallengeIdAndUuid(challengeId, uuid);
    }

    @GetMapping("/challenge/total-steps")
    public Mono<ResponseDto<Integer>> findChallengeTotalStepsByApiKeyAndChallengeIdAndUuid(
            @RequestParam(value = "apiKey") String apiKey,
            @RequestParam(value = "challengeId") String challengeId,
            @RequestParam(value = "uuid") String uuid) {

        return challengeTotalStepsFindByChallengeIdAndUuidService
                .findByChallengeIdAndUuid(challengeId, uuid);
    }

    @GetMapping("/challenge/bundle")
    public Mono<ResponseDto<List<String>>> findParticipationChallengesByApikeyAndUuid(
            @RequestParam(value = "apiKey") String apiKey,
            @RequestParam(value = "uuid") String uuid) {

        return challengeIdsFindByApiKeyAndUuidService.findChallengeIdsByApiKeyAndUuid(apiKey, uuid);
    }
}
