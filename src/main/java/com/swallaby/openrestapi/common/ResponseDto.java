package com.swallaby.openrestapi.common;

import com.swallaby.openrestapi.common.enums.ResponseFailEnum;
import com.swallaby.openrestapi.common.enums.ResponseSuccessEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseDto<T> {

    private String code;

    private String message;

    private T data;

    public static <T> ResponseDto<T> success(T data) {

        ResponseDto<T> response =
                ResponseDto.<T>builder().code(ResponseSuccessEnum.SUCCESS.getCode())
                        .message(ResponseSuccessEnum.SUCCESS.getMessage()).data(data).build();
        log.info("Success API Response: {}", response.toString());
        return response;
    }

    public static <T> ResponseDto<T> success() {
        return success(null);
    }

    public static <T> ResponseDto<T> fail(String code, String message, T data) {
        ResponseDto<T> response =
                ResponseDto.<T>builder().code(code).message(message).data(data).build();
        log.error("Failed API Response: {}", response.toString());
        return response;
    }

    public static <T> ResponseDto<String> fail(ResponseFailEnum responseFail) {
        return fail(responseFail.getCode(), responseFail.getMessage(), "");
    }

    public static <T> ResponseDto<String> fail() {
        return fail(ResponseFailEnum.SERVER_ERROR);
    }

    @Builder
    public ResponseDto(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}

