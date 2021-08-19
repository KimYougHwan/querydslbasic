package com.swallaby.openrestapi.controller.document;

import java.io.Serializable;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Docs implements Serializable {

    private static final long serialVersionUID = -6160305288319319157L;

    Map<String, String> apiResponseSuccessCodes;
    Map<String, String> apiResponseFailCodes;

    @Builder(builderClassName = "TestBuilder", builderMethodName = "testBuilder")
    private Docs(Map<String, String> apiResponseSuccessCodes,
            Map<String, String> apiResponseFailCodes) {

        this.apiResponseSuccessCodes = apiResponseSuccessCodes;
        this.apiResponseFailCodes = apiResponseFailCodes;
    }
}
