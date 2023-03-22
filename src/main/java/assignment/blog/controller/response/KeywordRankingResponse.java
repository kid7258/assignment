package assignment.blog.controller.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KeywordRankingResponse {
    private List<KeywordRanking> results;

    @AllArgsConstructor
    @Getter
    public static class KeywordRanking {
        private String keyword;
        private long count;
    }
}
