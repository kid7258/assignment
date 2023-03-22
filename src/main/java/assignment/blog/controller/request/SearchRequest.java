package assignment.blog.controller.request;

import assignment.blog.enums.SortType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class SearchRequest {
    private SortType sort = SortType.ACCURACY;
    private String keyword;
}
