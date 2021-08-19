package com.swallaby.openrestapi.entity;

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

@Table(name = "CHALLENGE_ATTEND_USER")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString
@IdClass(ChallengeAttendUserIdClass.class)
public class ChallengeAttendUser {

    @Id
    @Lob
    @Column(name = "CHALLENGE_ID", columnDefinition = "BINARY(16)")
    private byte[] challengeId;

    @Id
    @Lob
    @Column(name = "USER_ID", columnDefinition = "BINARY(16)")
    private byte[] userId;

    @Column(name = "CHALLENGE_GROUP_ID")
    private byte[] challengeGroupId;

    @Column(name = "CHALLENGE_ATTEND_TIME")
    private int challengeAttendTime;
    @Column(name = "CHALLENGE_ATTEND_PROGRESS")
    private int challengeAttendProgress;
    @Column(name = "CHALLENGE_ATTEND_CONTENT")
    private String challengeAttendContent;
    @Column(name = "CHALLENGE_ATTEND_STATUS")
    private int challengeAttendStatus;
    @Column(name = "CHALLENGE_ATTEND_COMPLETE_REQUEST_TIME")
    private int challengeAttendCompleteReequestTime;
    @Column(name = "CHALLENGE_ATTEND_COMPLETED_TIME")
    private int challengeAttendCompletedTime;
    @Column(name = "CHALLENGE_ATTEND_LAST_SYNC_TIME")
    private int challengeAttendLastSyncTime;
    @Column(name = "CHALLENGE_ATTEND_COUNT")
    private int challengeAttendCount;

}
