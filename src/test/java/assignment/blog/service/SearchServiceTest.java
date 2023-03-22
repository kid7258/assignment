package assignment.blog.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import assignment.blog.controller.request.SearchRequest;
import assignment.blog.controller.response.SearchResponse;
import assignment.blog.entity.SearchWord;
import assignment.blog.enums.SortType;
import assignment.blog.mapper.NaverSearchMapper;
import assignment.blog.repository.SearchWordRepository;
import assignment.blog.thirdparty.KakaoApi;
import assignment.blog.thirdparty.NaverApi;
import assignment.blog.thirdparty.SearchSourceFactory;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @InjectMocks
    private SearchService searchService;
    @Mock
    private SearchSourceFactory searchSourceFactory;
    @Mock
    private SearchSourceTypeService searchSourceTypeService;
    @Mock
    private SearchWordRepository searchWordRepository;
    private MockWebServer mockWebServer;
    private String mockServerUrl;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        objectMapper = new ObjectMapper();

        mockWebServer = new MockWebServer();
        mockServerUrl = mockWebServer.url("/blogs").toString();
        mockWebServer.enqueue(
            new MockResponse().setResponseCode(HttpStatus.SERVICE_UNAVAILABLE.value())
                .setHeader("Content-Type", MediaType.APPLICATION_JSON)
                .setBody(objectMapper.writeValueAsString(createSearchResponse()))
        );

        mockWebServer.enqueue(
            new MockResponse().setResponseCode(HttpStatus.OK.value())
                .setHeader("Content-Type", MediaType.APPLICATION_JSON)
                .setBody(objectMapper.writeValueAsString(createNaverSearchMapper()))
        );
    }

    SearchResponse createSearchResponse() {
        return new SearchResponse(
            new SearchResponse.Meta(false, 10, 10),
            List.of(new SearchResponse.Document("제목", "내용", "URL", "블로그명", "썸네일", new Date()))
        );
    }

    NaverSearchMapper createNaverSearchMapper() {
        return new NaverSearchMapper(new Date(), 10, 10, 10, Collections.emptyList());
    }

    @DisplayName("카카오 API가 비정상적일 경우 네이버 API로 조회한다.")
    @Test
    void searchApiTest() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setSort(SortType.ACCURACY);
        searchRequest.setKeyword("기굥이");

        when(searchSourceFactory.getSearchSource(searchSourceTypeService.getSearchSourceType())).thenReturn(
            new KakaoApi(new RestTemplate(), mockServerUrl));

        assertThatThrownBy(() -> searchService.search(searchRequest, PageRequest.of(1, 10))).isInstanceOf(
            RestClientException.class);

        when(searchSourceFactory.getSearchSource(searchSourceTypeService.getSearchSourceType())).thenReturn(
            new NaverApi(new RestTemplate(), mockServerUrl));
        when(searchWordRepository.save(any())).thenReturn(new SearchWord("기굥이"));

        searchService.search(searchRequest, PageRequest.of(1, 10));
    }
}