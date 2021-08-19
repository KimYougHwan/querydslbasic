package com.swallaby.openrestapi.challenge.dto;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChallengeInformationDto implements Serializable {

    private static final long serialVersionUID = 3055043576000644695L;

    private final String challengeName;
    private final int challengeStartDate;
    private final int challengeEndDate;
    private final int challengeEnable;
    private final int maxinumStepsEarnedPerDay;

    @Builder
    public ChallengeInformationDto(String challengeName, int challengeStartDate,
            int challengeEndDate, int challengeEnable, int maxinumStepsEarnedPerDay) {
        this.challengeName = challengeName;
        this.challengeStartDate = challengeStartDate;
        this.challengeEndDate = challengeEndDate;
        this.challengeEnable = challengeEnable;
        this.maxinumStepsEarnedPerDay = maxinumStepsEarnedPerDay;
    }


}
