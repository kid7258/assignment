package assignment.blog.thirdparty;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import assignment.blog.enums.SearchSourceType;
import lombok.Getter;

@Component
@Getter
public class SearchSourceFactory {
    private Map<SearchSourceType, SearchSource> searchSourceMap = new HashMap<>();

    public SearchSourceFactory() {
        searchSourceMap.put(SearchSourceType.KAKAO,
            new KakaoApi(new RestTemplate(), "https://dapi.kakao.com/v2/search/blog"));
        searchSourceMap.put(SearchSourceType.NAVER,
            new NaverApi(new RestTemplate(), "https://openapi.naver.com/v1/search/blog.json"));
    }

    public SearchSource getSearchSource(SearchSourceType searchSourceType) {
        return this.searchSourceMap.get(searchSourceType);
    }
}
