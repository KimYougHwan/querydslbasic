package com.swallaby.openrestapi.controller.document;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.swallaby.openrestapi.common.ResponseDto;
import com.swallaby.openrestapi.common.enums.EnumType;
import com.swallaby.openrestapi.common.enums.ResponseFailEnum;
import com.swallaby.openrestapi.common.enums.ResponseSuccessEnum;

@RestController
public class EnumViewController {

    @GetMapping("/docs")
    public ResponseDto<Docs> findAll() {

        Map<String, String> apiResponseSuccessCodes = getDocs(ResponseSuccessEnum.values());
        Map<String, String> apiResponsefailCodes = getDocs(ResponseFailEnum.values());

        Docs docs = Docs.testBuilder().apiResponseFailCodes(apiResponsefailCodes)
                .apiResponseSuccessCodes(apiResponseSuccessCodes).build();

        return ResponseDto.success(docs);
    }

    private Map<String, String> getDocs(EnumType[] enumTypes) {
        return Arrays.stream(enumTypes)
                .collect(Collectors.toMap(EnumType::getCode, EnumType::getMessage));
    }
}
