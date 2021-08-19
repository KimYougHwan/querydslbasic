package com.swallaby.openrestapi.challenge.repository;

import static com.querydsl.core.types.ExpressionUtils.count;
import static com.swallaby.openrestapi.entity.QChallengeAttendUser.challengeAttendUser;
import static com.swallaby.openrestapi.entity.QChallengeMaster.challengeMaster;
import static com.swallaby.openrestapi.entity.QOpenRestApiKey.openRestApiKey;
import static com.swallaby.openrestapi.entity.QUser.user;
import java.time.Instant;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.swallaby.openrestapi.challenge.dto.ChallengeInformationDto;
import com.swallaby.openrestapi.challenge.dto.UserCountChallenge;
import com.swallaby.openrestapi.common.ResponseDto;
import com.swallaby.openrestapi.common.enums.ResponseFailEnum;
import com.swallaby.openrestapi.common.exception.WalkonRestApiException;
import com.swallaby.openrestapi.entity.ChallengeMaster;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Repository
@Slf4j
public class ChallengeRepositorySupoortImpl extends QuerydslRepositorySupport
        implements ChallengeRepositorySupoort {

    private final JPAQueryFactory jpaQueryFactory;
    private static final int CHALLENGE_ENABLE_CODE = 1;

    public ChallengeRepositorySupoortImpl(JPAQueryFactory jpaQueryFactory,
            EntityManager entityManager) {
        super(ChallengeMaster.class);
        super.setEntityManager(entityManager);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Mono<ResponseDto<List<String>>> findByApiKey(String apiKey) {

        long now = Instant.now().getEpochSecond();
        return Mono.fromCallable(() -> {
            List<String> result = jpaQueryFactory.select(challengeMaster.challengeId)
                    .from(openRestApiKey).join(challengeMaster)
                    .on(openRestApiKey.custmoerId.eq(challengeMaster.customerId))
                    .where(openRestApiKey.key.eq(apiKey),
                            challengeMaster.challengeStatus.eq(CHALLENGE_ENABLE_CODE),
                            challengeMaster.challengeAttendStime.loe(now),
                            challengeMaster.challengeAttendEtime.goe(now))
                    .fetch().stream().map(x -> {
                        StringBuilder sb = new StringBuilder();
                        for (byte b : x) {
                            sb.append(String.format("%02X", b & 0xff));
                        }

                        return sb.toString();
                    }).toList();

            if (result.isEmpty()) {
                throw new WalkonRestApiException(ResponseFailEnum.CHALLENGE_NOT_FOUND);
            }
            return ResponseDto.success(result);
        }).publishOn(Schedulers.boundedElastic()).doOnError(
                e -> Mono.error(new WalkonRestApiException(ResponseFailEnum.SERVER_ERROR)));
    }

    @Override
    public Mono<ResponseDto<String>> findByApiKeyAndPhoneNumber(String apiKey, String phoneNumber) {

        long now = Instant.now().getEpochSecond();

        return Mono.fromCallable(() -> {
            List<String> userId = jpaQueryFactory
                    .select(Projections.constructor(UserCountChallenge.class,
                            ExpressionUtils.as(JPAExpressions.select(count(user.id)).from(user)
                                    .where(user.phoneNumber.eq(phoneNumber), user.auth.eq(true),
                                            user.deleted.eq(false)),
                                    "userCount"),
                            user.id))
                    .from(user).join(challengeAttendUser).on(user.id.eq(challengeAttendUser.userId))
                    .join(challengeMaster)
                    .on(challengeAttendUser.challengeId.eq(challengeMaster.challengeId))
                    .join(openRestApiKey)
                    .on(challengeMaster.customerId.eq(openRestApiKey.custmoerId))
                    .where(user.phoneNumber.eq(phoneNumber), openRestApiKey.key.eq(apiKey),
                            user.auth.eq(true), user.deleted.eq(false),
                            challengeMaster.challengeStatus.eq(CHALLENGE_ENABLE_CODE),
                            challengeMaster.challengeAttendStime.loe(now),
                            challengeMaster.challengeAttendEtime.goe(now))
                    .groupBy(user.id).fetch().stream().map(x -> {

                        if (x.getUserCount() > 1L) {
                            throw new WalkonRestApiException(ResponseFailEnum.USER_TO_MANY);
                        } else if (x.getUserCount() == 0L) {
                            throw new WalkonRestApiException(ResponseFailEnum.USER_NOT_FOUND);
                        }

                        StringBuilder sb = new StringBuilder();
                        for (byte b : x.getUserId()) {
                            sb.append(String.format("%02X", b & 0xff));
                        }
                        sb.insert(2, "SD");
                        return sb.toString();
                    }).toList();
            if (userId.isEmpty()) {
                log.debug(userId.toString());
                throw new WalkonRestApiException(ResponseFailEnum.USER_NOT_FOUND);
            } else if (userId.size() > 1) {
                log.debug(userId.toString());
                throw new WalkonRestApiException(ResponseFailEnum.USER_TO_MANY);
            }
            return ResponseDto.success(userId.get(0));
        }).publishOn(Schedulers.boundedElastic()).doOnError(
                e -> Mono.error(new WalkonRestApiException(ResponseFailEnum.SERVER_ERROR)));
    }

    @Override
    public Mono<ResponseDto<ChallengeInformationDto>> findByApiKeyAndChallengeId(String apiKey,
            byte[] challengeId) {

        return Mono.fromCallable(() -> {
            List<ChallengeInformationDto> challengeInformationDtos = jpaQueryFactory
                    .select(Projections.constructor(ChallengeInformationDto.class,
                            challengeMaster.challengeTitle, challengeMaster.challengeAttendStime,
                            challengeMaster.challengeAttendEtime, challengeMaster.challengeStatus,
                            challengeMaster.challengeStepsMax))
                    .from(challengeMaster).join(openRestApiKey)
                    .on(challengeMaster.customerId.eq(openRestApiKey.custmoerId))
                    .where(challengeMaster.challengeId.eq(challengeId),
                            openRestApiKey.key.eq(apiKey))
                    .fetch().stream().map(x -> x).toList();
            if (challengeInformationDtos.isEmpty()) {
                throw new WalkonRestApiException(ResponseFailEnum.NOT_FOUND);
            } else if (challengeInformationDtos.size() > 2) {
                throw new WalkonRestApiException(ResponseFailEnum.SERVER_ERROR);
            }

            return ResponseDto.success(challengeInformationDtos.get(0));
        }).publishOn(Schedulers.boundedElastic()).doOnError(
                e -> Mono.error(new WalkonRestApiException(ResponseFailEnum.SERVER_ERROR)));
    }

    @Override
    public Mono<ResponseDto<Boolean>> findByChallengeIdAndUuid(byte[] challengeId, byte[] uuid) {

        return Mono.fromCallable(() -> {
            Long count = jpaQueryFactory.from(challengeAttendUser)
                    .where(challengeAttendUser.challengeId.eq(challengeId),
                            challengeAttendUser.userId.eq(uuid))
                    .groupBy(challengeAttendUser.userId)
                    .select(challengeAttendUser.userId, challengeAttendUser.userId.count())
                    .fetchCount();

            boolean result = false;

            if (count > 0L)
                result = true;

            return ResponseDto.success(result);
        }).publishOn(Schedulers.boundedElastic()).doOnError(
                e -> Mono.error(new WalkonRestApiException(ResponseFailEnum.SERVER_ERROR)));
    }

    @Override
    public Mono<ResponseDto<Integer>> findChallengeTotalStepsByApiKeyAndChallengeIdAndUuid(
            byte[] challengeId, byte[] uuid) {

        return Mono.fromCallable(() -> {
            Object queryResult = jpaQueryFactory.select(challengeAttendUser.challengeAttendProgress)
                    .from(challengeAttendUser)
                    .where(challengeAttendUser.challengeId.eq(challengeId),
                            challengeAttendUser.userId.eq(uuid))
                    .fetchOne();
            int steps = queryResult == null ? 0 : (int) queryResult;
            return ResponseDto.success(steps);
        }).publishOn(Schedulers.boundedElastic()).switchIfEmpty(Mono.just(ResponseDto.success(0)))
                .doOnError(
                        e -> Mono.error(new WalkonRestApiException(ResponseFailEnum.SERVER_ERROR)));
    }

    @Override
    public Mono<ResponseDto<List<String>>> findChallengesFindByApiKeyAndUuid(String apiKey,
            byte[] uuid) {

        long now = Instant.now().getEpochSecond();

        return Mono.fromCallable(() -> {
            List<String> challenges = jpaQueryFactory.select(challengeMaster.challengeId)
                    .from(challengeMaster).join(openRestApiKey)
                    .on(challengeMaster.customerId.eq(openRestApiKey.custmoerId))
                    .join(challengeAttendUser)
                    .on(challengeAttendUser.challengeId.eq(challengeMaster.challengeId))
                    .where(challengeAttendUser.userId.eq(uuid), openRestApiKey.key.eq(apiKey),
                            challengeMaster.challengeStatus.eq(CHALLENGE_ENABLE_CODE),
                            challengeMaster.challengeAttendStime.loe(now),
                            challengeMaster.challengeAttendEtime.goe(now))
                    .fetch().stream().map(x -> {
                        StringBuilder sb = new StringBuilder();
                        for (byte b : x) {
                            sb.append(String.format("%02X", b & 0xff));
                        }

                        return sb.toString();
                    }).toList();

            if (challenges.isEmpty()) {
                throw new WalkonRestApiException(ResponseFailEnum.NOT_FOUND);
            }
            return ResponseDto.success(challenges);

        }).publishOn(Schedulers.boundedElastic()).doOnError(
                e -> Mono.error(new WalkonRestApiException(ResponseFailEnum.SERVER_ERROR)));
    }
}
