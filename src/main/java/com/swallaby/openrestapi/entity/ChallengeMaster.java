package com.swallaby.openrestapi.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.geo.Point;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "CHALLENGE_MASTER")
@Entity(name = "CHALLENGE_MASTER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
public class ChallengeMaster {

    @Id
    @Column(name = "CHALLENGE_ID", columnDefinition = "BINARY(16)")
    private byte[] challengeId;
    @Column(name = "CHALLENGE_TYPE")
    private int challengeType;
    @Column(name = "CHALLENGE_TITLE")
    private String challengeTitle;
    @Column(name = "CHALLENGE_SPONSORED")
    private byte[] challengeSponsored;
    @Column(name = "CHALLENGE_VIEW_STIME")
    private int challengeViewStime;
    @Column(name = "CHALLENGE_VIEW_ETIME")
    private int challengeViewEtime;
    @Column(name = "CHALLENGE_ATTEND_STIME")
    private int challengeAttendStime;
    @Column(name = "CHALLENGE_ATTEND_ETIME")
    private int challengeAttendEtime;
    @Column(name = "CHALLENGE_STATUS")
    private int challengeStatus;
    @Column(name = "CHALLENGE_VIEW_TYPE")
    private int challengeViewType;
    @Column(name = "CHALLENGE_GOAL")
    private int challengeGoal;
    @Column(name = "CHALLENGE_IMGPATH_MAIN")
    private String challengeImgPathMain;
    @Column(name = "CHALLENGE_IMGPATH_SUB")
    private String challengeImgPathSub;
    @Column(name = "CHALLENGE_DESCRIPTION")
    private String challengeDescription;
    @Column(name = "CHALLENGE_REWARD_TYPE")
    private int challengeRewardType;
    @Column(name = "CHALLENGE_CREATE_TIME")
    private Timestamp challengeCreateTime;
    @Column(name = "CHALLENGE_EDIT_TIME")
    private Timestamp challengeEditTime;
    @Column(name = "CHALLENGE_CREATOR")
    private String challengeCreator;
    @Column(name = "CHALLENGE_EDITOR")
    private String challengeEditor;
    @Column(name = "CHALLENGE_STEPS_PER_POINT")
    private int challengeStepsRerPoint;
    @Column(name = "CHALLENGE_STEPS_MIN")
    private int challengeStepsMin;
    @Column(name = "CHALLENGE_STEPS_MAX")
    private int challengeStepsMax;
    @Column(name = "CHALLENGE_WALK_COURSE_ID")
    private byte[] challengeWalkCourseId;
    @Column(name = "CHALLENGE_LOCATION")
    private Point challengeLocation;
    @Column(name = "CHALLENGE_GEOHASH")
    private String challengeGeohash;
    @Column(name = "CHALLENGE_STAMP_TOUR_ID")
    private byte[] challengeStampTourId;
    @Column(name = "CHALLENGE_INFO_DURATION")
    private String challengeInfoDuration;
    @Column(name = "CHALLENGE_INFO_REWARD")
    private String challengeInfoReward;
    @Column(name = "CHALLENGE_INFO_GOAL")
    private String challengeInfoGoal;
    @Column(name = "CHALLENGE_WALK_ZONE_ID")
    private byte[] challengeWalkZoneId;
    @Column(name = "CHALLENGE_FILTER")
    private int challengeFilter;
    @Column(name = "CHALLENGE_ATTEND_COUNT")
    private int challengeAttendCount;
    @Column(name = "CUSTOMER_ID")
    private int customerId;

}
