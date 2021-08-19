package com.swallaby.openrestapi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseFailEnum implements EnumType {

    NONE("NONE", "NONE"), BAD_PARAMETER("-1", "요청 파라미터가 잘못되었습니다."), NOT_FOUND("-2",
            "리소스를 찾지 못했습니다."), SERVER_ERROR("-3", "서버 에러입니다."), NOT_AUTHORIZED_CHALLENGE("-4",
                    "권한이 있는지 챌린지가 아닙니다."), USER_NOT_FOUND("-5",
                            "사용자 정보를 찾을 수 없습니다."), CHALLENGE_NOT_FOUND("-6",
                                    "챌린지 정보를 찾을 수 없습니다."), USER_TO_MANY("-7",
                                            "핸드폰 번호로 검색 되는 사용자 수가 많습니다."), NOT_AUTHORIZED("-8",
                                                    "apiKey를 확인해 주세요.");

    private final String code;
    private final String message;

    @Override
    public String getId() {

        return name();
    }


}
