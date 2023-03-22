package assignment.blog.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

import assignment.blog.controller.request.SearchRequest;
import assignment.blog.controller.response.SearchResponse;
import assignment.blog.entity.SearchWord;
import assignment.blog.exception.ApiException;
import assignment.blog.repository.SearchWordRepository;
import assignment.blog.thirdparty.SearchSourceFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SearchService {
    private final SearchSourceFactory searchSourceFactory;
    private final SearchSourceTypeService searchSourceTypeService;
    private final SearchWordRepository searchWordRepository;

    @Transactional
    public SearchResponse search(SearchRequest request, Pageable pageable) {
        SearchResponse searchResponse;

        try {
            searchResponse = searchSourceFactory.getSearchSource(searchSourceTypeService.getSearchSourceType())
                .searchBlog(request, pageable);
        } catch (RestClientException e) {
            searchSourceTypeService.changeSearchSourceType();
            throw new ApiException("서버가 원활하지 않습니다.", e);
        }

        insertOrUpdateCount(request.getKeyword());

        return searchResponse;
    }

    @Transactional
    public void insertOrUpdateCount(String keyword) {
        searchWordRepository.findByKeyword(keyword).ifPresentOrElse(
            searchWord -> searchWordRepository.updateCount(searchWord.getId()),
            () -> searchWordRepository.save(new SearchWord(keyword)));
    }
}
