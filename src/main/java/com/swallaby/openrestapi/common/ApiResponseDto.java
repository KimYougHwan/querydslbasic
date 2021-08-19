package com.swallaby.openrestapi.common;

import com.swallaby.openrestapi.common.enums.ResponseSuccessEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class ApiResponseDto<T> {

    private ResponseSuccessEnum code;
    private String message;
    private T data;

    private ApiResponseDto(ResponseSuccessEnum status, T data) {
        this.bindStatus(status);
        this.data = data;
    }

    private void bindStatus(ResponseSuccessEnum status) {
        this.code = status;
        this.message = status.getMessage();
    }

    public static <T> ApiResponseDto<T> createOK(T data) {
        return new ApiResponseDto<>(ResponseSuccessEnum.SUCCESS, data);
    }
}
