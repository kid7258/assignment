package assignment.blog.mapper;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NaverSearchMapper {
    private Date lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<Item> items;

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class Item {
        private String title;
        private String link;
        private String description;
        private String bloggername;
        private String bloggerlink;
        private String postdate;
    }
}
