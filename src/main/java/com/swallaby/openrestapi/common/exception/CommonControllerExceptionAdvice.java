package com.swallaby.openrestapi.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.swallaby.openrestapi.common.ResponseDto;

@RestControllerAdvice
public class CommonControllerExceptionAdvice {

    @ExceptionHandler(WalkonRestApiException.class)
    public ResponseDto<Object> walkonException(WalkonRestApiException ex) {

        return ResponseDto.fail(ex.getCode(), ex.getMessage(), "");

    }
}
