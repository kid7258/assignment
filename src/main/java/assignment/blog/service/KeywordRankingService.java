package assignment.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import assignment.blog.controller.response.KeywordRankingResponse;
import assignment.blog.entity.SearchWord;
import assignment.blog.repository.SearchWordRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class KeywordRankingService {
    private final SearchWordRepository searchWordRepository;

    @Transactional(readOnly = true)
    public KeywordRankingResponse getKeywordRankingTop10() {
        List<SearchWord> searchWords = searchWordRepository.findAllByKeywordOrderByCountDesc();
        List<KeywordRankingResponse.KeywordRanking> keywordRankings = searchWords.stream()
            .limit(10)
            .map(searchWord -> new KeywordRankingResponse.KeywordRanking(searchWord.getKeyword(),
                searchWord.getCount()))
            .collect(Collectors.toList());

        return new KeywordRankingResponse(keywordRankings);
    }
}
