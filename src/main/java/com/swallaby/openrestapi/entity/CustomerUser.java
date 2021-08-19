package com.swallaby.openrestapi.entity;

import java.sql.Timestamp;
import javax.persistence.Table;
import org.springframework.data.annotation.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Table(name = "custmoer_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
public class CustomerUser {
    @Id
    private int id;
    private int customerId;
    private String userId;
    private String creator;
    private Timestamp createDate;

}
