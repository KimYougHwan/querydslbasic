package com.swallaby.openrestapi.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "challenge_attend")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString
@IdClass(ChallengeAttendUserIdClass.class)
public class ChallengeAttend {

    @Id
    @Lob
    @Column(name = "userId", columnDefinition = "BINARY(16)")
    private byte[] userId;

    @Id
    @Lob
    @Column(name = "challengeId", columnDefinition = "BINARY(16)")
    private byte[] challengeId;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    private int steps;

    private int result;

    private int attended;

    private Timestamp complateTime;

    private Timestamp lastUploadedTime;

    private int todaySteps;

    private Timestamp initRequestTime;


}
