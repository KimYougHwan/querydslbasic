package com.swallaby.openrestapi.entity;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ChallengeAttendUserIdClass implements Serializable {

    private static final long serialVersionUID = -8670393010584731747L;

    private byte[] challengeId;
    private byte[] userId;

}
