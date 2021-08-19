package com.swallaby.openrestapi.challenge.controller;

import static com.swallaby.openrestapi.document.utils.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.swallaby.openrestapi.challenge.dto.ChallengeInformationDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureRestDocs
class ChallengeRestControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    String apiKey = "075285d9-986c-4fa8-81f3-49ef5affcd56";

    String phoneNumber = "+1111111";
    String phoneNumber2 = "+821030574414";

    String challengeId = "A1496D07E8724060E9CE92C022A0AF13";

    String uuid = "1CKKDB51DBF0BE4A5FA48DBF1350BFBB89";

    @Test
    @DisplayName("api Key로 챌린지 아이디 가져오기")
    void findChallengesByApiKeyTest() throws Exception {

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/challenges").queryParam("apiKey", apiKey).build())
                .exchange().expectStatus().isOk().expectBody()
                .consumeWith(document("walkon/challenge/{method-name}", getDocumentResponse(),
                        requestParameters(parameterWithName("apiKey").description("발급받은 apiKey")),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING)
                                        .description("성공, 실패 코드 "),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("성공 실패 메세지"),
                                fieldWithPath("data").type(JsonFieldType.ARRAY)
                                        .description("챌린지 리스트").optional())));
    }

    @Test
    @DisplayName("핸드폰 번호로 챌린저 유저 알아 내기")
    void findChallengeUserIdByPhoneNumberTest() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/challenge/user").queryParam("apiKey", apiKey)
                        .queryParam("phoneNumber", phoneNumber).build())
                .exchange().expectStatus().isOk().expectBody()
                .consumeWith(document("walkon/challenge/user/{method-name}", getDocumentResponse(),
                        requestParameters(parameterWithName("apiKey").description("발급받은 apiKey"),
                                parameterWithName("phoneNumber").description("고객 핸드폰 번호")),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING)
                                        .description("성공, 실패 코드 "),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("성공 실패 메세지"),
                                fieldWithPath("data").type(JsonFieldType.STRING)
                                        .description("고객 아이디"))));
    }

    @Test
    @DisplayName("apiKey와 챌린지 아이디로 해당 챌린지 정보 알아내기")
    void findChallengeInformationByApiKeyAndChallengeIdTest() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/challenge").queryParam("apiKey", apiKey)
                        .queryParam("challengeId", challengeId).build())
                .exchange().expectStatus().isOk().expectBody()
                .consumeWith(document("walkon/challenge/{method-name}", getDocumentResponse(),
                        requestParameters(parameterWithName("apiKey").description("발급받은 apiKey"),
                                parameterWithName("challengeId").description("챌린지 아이디")),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING)
                                        .description("성공, 실패 코드 "),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("성공 실패 메세지"),
                                fieldWithPath("data")
                                        .type(ChallengeInformationDto.class.getSimpleName())
                                        .optional().description("챌린지 정보"),
                                fieldWithPath("data.challengeName").type(JsonFieldType.STRING)
                                        .description("챌린지명"),
                                fieldWithPath("data.challengeStartDate").type(JsonFieldType.NUMBER)
                                        .description("챌린지 시작일자"),
                                fieldWithPath("data.challengeEndDate").type(JsonFieldType.NUMBER)
                                        .description("챌린지 종료일자"),
                                fieldWithPath("data.challengeEnable").type(JsonFieldType.NUMBER)
                                        .description("챌린지 활성화 여부 0:비노출 1: 운영"),
                                fieldWithPath("data.maxinumStepsEarnedPerDay")
                                        .type(JsonFieldType.NUMBER).description("하루 최대 인정 걸음 수")
                                        .optional())));
    }

    @Test
    @DisplayName("apiKey와 챌린지 아이디 그리고 고객 아이디로 챌린지 참여 여부 확인하기")
    void findChallengeParticipationStatusByApiKeyAndChallengeIdAndUuidTest() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/challenge/participation/status")
                        .queryParam("apiKey", apiKey).queryParam("challengeId", challengeId)
                        .queryParam("uuid", uuid).build())
                .exchange().expectStatus().isOk().expectBody()
                .consumeWith(document("walkon/challenge/{method-name}", getDocumentResponse(),
                        requestParameters(parameterWithName("apiKey").description("발급받은 apiKey"),
                                parameterWithName("challengeId").description("챌린지 아이디"),
                                parameterWithName("uuid").description("walkon에서 제공한 고객 아이디")),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING)
                                        .description("성공, 실패 코드 "),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("성공 실패 메세지"),
                                fieldWithPath("data").type(JsonFieldType.BOOLEAN)
                                        .description("참여 여부"))));
    }

    @Test
    @DisplayName("apiKey와 챌린지 아이디, 고객아이디, 날짜 범위로 날짜 범위 안의 누적 걸음수 제공")
    void findChallengeTotalStepsByApiKeyAndChallengeIdAndUuidTest() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/challenge/total-steps")
                        .queryParam("apiKey", apiKey).queryParam("challengeId", challengeId)
                        .queryParam("uuid", uuid).build())
                .exchange().expectStatus().isOk().expectBody()
                .consumeWith(document("walkon/challenge/{method-name}", getDocumentResponse(),
                        requestParameters(parameterWithName("apiKey").description("발급받은 apiKey"),
                                parameterWithName("challengeId").description("챌린지 아이디"),
                                parameterWithName("uuid").description("walkon에서 제공한 고객 아이디")),

                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING)
                                        .description("성공, 실패 코드 "),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("성공 실패 메세지"),
                                fieldWithPath("data").type(JsonFieldType.NUMBER)
                                        .description("파라미터 정보에 따른 누적 걸음수"))));
    }

    @Test
    @DisplayName("apiKey와 고객아이도로 해당 apiKey 안에 참여 하고 있는 챌린지 알아내기")
    void findParticipationChallengesByApikeyAndUuidTest() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/challenge/bundle").queryParam("apiKey", apiKey)
                        .queryParam("uuid", uuid).build())
                .exchange().expectStatus().isOk().expectBody()
                .consumeWith(document("walkon/challenge/{method-name}", getDocumentResponse(),
                        requestParameters(parameterWithName("apiKey").description("발급받은 apiKey"),
                                parameterWithName("uuid").description("walkon에서 제공한 고객 아이디")),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.STRING)
                                        .description("성공, 실패 코드 "),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("성공 실패 메세지"),
                                fieldWithPath("data").type(JsonFieldType.ARRAY)
                                        .description("챌린지 아이디 리스트"))));
    }


}
