package assignment.blog.thirdparty;

import org.springframework.data.domain.Pageable;

import assignment.blog.controller.request.SearchRequest;
import assignment.blog.controller.response.SearchResponse;

public interface SearchSource {
    SearchResponse searchBlog(SearchRequest request, Pageable pageable);
}
