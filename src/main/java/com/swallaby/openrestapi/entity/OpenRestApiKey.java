package com.swallaby.openrestapi.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "open_rest_api_key")
@Entity
@Getter
@ToString
public class OpenRestApiKey {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "customer_id")
    private int custmoerId;
    private String key;
    private int limit;
    private String creator;

    @Column(name = "create_date")
    private Timestamp createDate;

    public boolean isEnabled() {

        return this.id != 0;
    }

}
