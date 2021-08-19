package com.swallaby.openrestapi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseSuccessEnum implements EnumType {

    SUCCESS("0000", "OK");

    private final String code;
    private final String message;

    @Override
    public String getId() {

        return name();
    }
}
