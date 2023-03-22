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
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class KakaoApi implements SearchSource {
    private final RestTemplate restTemplate;
    private final String url;

    @Override
    public SearchResponse searchBlog(SearchRequest request, Pageable pageable) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "KakaoAK " + "8d11346faccff8254ed49991ee4d4153");
        Map<String, Object> params = new HashMap<>();
        params.put("query", request.getKeyword());
        params.put("sort", request.getSort().getKakao());
        params.put("page", pageable.getPageNumber() + 1);
        params.put("size", pageable.getPageSize());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<SearchResponse> response = restTemplate.exchange(
            url + "?query={query}&sort={sort}&page={page}&size={size}",
            HttpMethod.GET, entity, SearchResponse.class, params);

        return response.getBody();
    }
}
