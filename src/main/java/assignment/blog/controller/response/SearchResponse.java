package assignment.blog.controller.response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import assignment.blog.mapper.NaverSearchMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SearchResponse {
    private Meta meta;
    private List<Document> documents;

    public static SearchResponse convertedNaverSearchResponse(NaverSearchMapper mapper) {
        Meta meta = new Meta(mapper.getTotal() <= mapper.getDisplay() * mapper.getStart(), mapper.getTotal(),
            mapper.getTotal());

        List<Document> documents = mapper.getItems().stream()
            .map(item -> {
                try {
                    return new Document(item.getTitle(), item.getDescription(), item.getLink(), item.getBloggername(),
                        null, new SimpleDateFormat("yyyyMMdd").parse(item.getPostdate()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            })
            .collect(Collectors.toList());

        return new SearchResponse(meta, documents);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class Meta {
        @JsonProperty("is_end")
        private Boolean isEnd;
        @JsonProperty("pageable_count")
        private int pageableCount;
        @JsonProperty("total_count")
        private int totalCount;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class Document {
        private String title;
        private String contents;
        private String url;
        private String blogname;
        private String thumbnail;
        private Date datetime;
    }
}
