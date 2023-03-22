package assignment.blog.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import assignment.blog.controller.response.KeywordRankingResponse;
import assignment.blog.entity.SearchWord;
import assignment.blog.repository.SearchWordRepository;

@ExtendWith(MockitoExtension.class)
class KeywordRankingServiceTest {

    @InjectMocks
    private KeywordRankingService keywordRankingService;
    @Mock
    private SearchWordRepository searchWordRepository;

    @DisplayName("인기 검색어 목록 최대 10개의 검색 키워드를 조회한다.")
    @Test
    void keywordRankingTop10Test() {
        List<SearchWord> searchWords = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            searchWords.add(new SearchWord("기굥이" + i));
        }

        searchWords.add(new SearchWord("기굥이0"));

        when(searchWordRepository.findAllByKeywordOrderByCountDesc()).thenReturn(searchWords);
        KeywordRankingResponse keywordRankingTop10 = keywordRankingService.getKeywordRankingTop10();

        assertThat(keywordRankingTop10.getResults().size()).isEqualTo(10);
    }
}