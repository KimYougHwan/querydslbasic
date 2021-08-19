package com.swallaby.openrestapi.controller.document;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.restdocs.payload.PayloadDocumentation.beneathPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swallaby.openrestapi.common.ResponseDto;
import com.swallaby.openrestapi.document.utils.CustomResponseFieldsSnippet;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureWebTestClient
@AutoConfigureRestDocs
class CommonDocumentationTests {

    private WebTestClient webTestClient;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) {
        this.webTestClient = WebTestClient.bindToController(EnumViewController.class)
                .configureClient().filter(documentationConfiguration(restDocumentation)).build();
    }


    @Test
    @DisplayName("api rest docs 설정을 위한 function")
    void commonsTest() throws Exception {

        FluxExchangeResult<Docs> result = webTestClient.get().uri("/docs")
                .accept(MediaType.APPLICATION_JSON).exchange().returnResult(Docs.class);

        Docs docs = getData(result);

        result.consumeWith(document("walkon/common",
                customResponseFields("custom-response", null,
                        attributes(key("title").value("공통응답")),
                        fieldWithPath("code").type(JsonFieldType.STRING).description("결과코드"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("결과메시지"),
                        subsectionWithPath("data").description("데이터")),

                customResponseFields("custom-response",
                        beneathPath("data.apiResponseSuccessCodes")
                                .withSubsectionId("apiResponseSuccessCodes"),
                        attributes(key("title").value("성공 응답코드")),
                        enumConvertFieldDescriptor(docs.getApiResponseSuccessCodes())),
                customResponseFields("custom-response",
                        beneathPath("data.apiResponseFailCodes").withSubsectionId(
                                "apiResponseFailCodes"),
                        attributes(key("title").value("실패 응답코드")),
                        enumConvertFieldDescriptor(docs.getApiResponseFailCodes()))));

        assertThat(docs, is(not(nullValue())));
    }

    private static FieldDescriptor[] enumConvertFieldDescriptor(Map<String, String> enumValues) {

        return enumValues.entrySet().stream()
                .map(x -> fieldWithPath(x.getKey()).description(x.getValue()))
                .toArray(FieldDescriptor[]::new);
    }

    private Docs getData(FluxExchangeResult<Docs> docs) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        ResponseDto<Docs> apiResponseDto = objectMapper.readValue(docs.getResponseBodyContent(),
                new TypeReference<ResponseDto<Docs>>() {});

        return apiResponseDto.getData();
    }

    public static CustomResponseFieldsSnippet customResponseFields(String type,
            PayloadSubsectionExtractor<?> subsectionExtractor, Map<String, Object> attributes,
            FieldDescriptor... descriptors) {
        return new CustomResponseFieldsSnippet(type, subsectionExtractor,
                Arrays.asList(descriptors), attributes, true);
    }


}

