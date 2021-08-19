package com.swallaby.openrestapi.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "user")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
public class User {

    @Id
    @Lob
    @Column(name = "id", columnDefinition = "BINARY(16)")
    byte[] id;
    @Column(name = "phoneno")
    String phoneNumber;
    boolean auth;
    boolean deleted;
    String profile;
    Timestamp timestamp;
    String token;
}
