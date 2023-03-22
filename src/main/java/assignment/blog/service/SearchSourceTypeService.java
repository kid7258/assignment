package assignment.blog.service;

import org.springframework.stereotype.Service;

import assignment.blog.enums.SearchSourceType;

@Service
public class SearchSourceTypeService {
    private SearchSourceType searchSourceType = SearchSourceType.KAKAO;

    public SearchSourceType getSearchSourceType() {
        return searchSourceType;
    }

    public void changeSearchSourceType() {
        searchSourceType = searchSourceType == SearchSourceType.KAKAO ? SearchSourceType.NAVER : SearchSourceType.KAKAO;
    }
}
