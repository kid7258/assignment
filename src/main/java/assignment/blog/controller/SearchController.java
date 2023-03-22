package assignment.blog.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import assignment.blog.controller.request.SearchRequest;
import assignment.blog.service.KeywordRankingService;
import assignment.blog.service.SearchService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/blogs")
public class SearchController {
    private final SearchService searchService;
    private final KeywordRankingService keywordRankingService;

    @GetMapping
    public ResponseEntity<Object> search(SearchRequest request, Pageable pageable) {
        return ResponseEntity.ok(searchService.search(request, pageable));
    }

    @GetMapping("/keyword-ranking")
    public ResponseEntity<Object> getKeywordRanking() {
        return ResponseEntity.ok(keywordRankingService.getKeywordRankingTop10());
    }
}
