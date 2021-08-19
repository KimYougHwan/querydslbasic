package com.swallaby.openrestapi.common.exception;

import com.swallaby.openrestapi.common.enums.ResponseFailEnum;
import lombok.Getter;

@Getter
public class WalkonRestApiException extends RuntimeException {

    private static final long serialVersionUID = 4103663602260773242L;

    private final String code;

    public WalkonRestApiException(String code, String message) {
        super(message);
        this.code = code;
    }

    public WalkonRestApiException(ResponseFailEnum responseFail) {
        super(responseFail.getMessage());
        this.code = responseFail.getCode();

    }

    public WalkonRestApiException(Throwable throwable) {
        super(throwable);
        this.code = "-100";
    }

}
