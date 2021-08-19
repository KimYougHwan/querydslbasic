package com.swallaby.openrestapi.document.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public interface DocumentLinkGenerator {

    static String generateLinkCode(DocUrl docUrl) {
        return String.format("link:common/%s.html[%s %s,role=\"popup\"]", docUrl.pageId,
                docUrl.text, "코드");
    }

    static String generateText(DocUrl docUrl) {
        return String.format("%s %s", docUrl.text, "코드명");
    }

    @RequiredArgsConstructor
    enum DocUrl {
        FAIL_CODE("failCode", "실패 코드"), SUCCESS_CODE("successCode", "성공 코드");

        private final String pageId;
        @Getter
        private final String text;
    }
}
