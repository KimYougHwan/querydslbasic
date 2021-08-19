package com.swallaby.openrestapi.statistics.dto;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DailyTrendDto implements Serializable {

    private static final long serialVersionUID = 6406123254492281397L;

    private final int totalStep;
    private final int calorieConsumption;
    private final int moveDistance;
    private final int moveTime;

}
