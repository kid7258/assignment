package assignment.blog.thirdparty;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import assignment.blog.controller.request.SearchRequest;
import assignment.blog.controller.response.SearchResponse;
import assignment.blog.mapper.NaverSearchMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NaverApi implements SearchSource {
    private final RestTemplate restTemplate;
    private final String url;

    @Override
    public SearchResponse searchBlog(SearchRequest request, Pageable pageable) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("X-Naver-Client-Id", "PIHxx7JRuTUOKv2Kg1we");
        headers.add("X-Naver-Client-Secret", "zW6208zh4j");
        Map<String, Object> params = new HashMap<>();
        params.put("query", request.getKeyword());
        params.put("sort", request.getSort().getNaver());
        params.put("start", pageable.getPageNumber() + 1);
        params.put("display", pageable.getPageSize());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<NaverSearchMapper> response = restTemplate.exchange(
            url + "?query={query}&display={display}&start={start}&sort={sort}",
            HttpMethod.GET, entity, NaverSearchMapper.class, params);

        return SearchResponse.convertedNaverSearchResponse(response.getBody());
    }
}
