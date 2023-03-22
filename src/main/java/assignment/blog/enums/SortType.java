package assignment.blog.enums;

import lombok.Getter;

@Getter
public enum SortType {
    ACCURACY("accuracy", "sim"),
    RECENCY("recency", "date");

    private final String kakao;
    private final String naver;

    SortType(String kakao, String naver) {
        this.kakao = kakao;
        this.naver = naver;
    }
}
