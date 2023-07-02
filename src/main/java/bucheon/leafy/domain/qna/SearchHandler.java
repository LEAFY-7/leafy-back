package bucheon.leafy.domain.qna;

import lombok.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;
import static java.util.Objects.requireNonNullElse;
@Data
@NoArgsConstructor
public class SearchHandler {
    private Integer page = 1;
    private Integer pageSize = DEFAULT_PAGE_SIZE;
    private List<String> option = new ArrayList<>();
    public static final int MIN_PAGE_SIZE = 5;
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int MAX_PAGE_SIZE = 50;

    public SearchHandler(Integer page, Integer pageSize) {
        this(page, pageSize, "", "");
    }

    public SearchHandler(Integer page, Integer pageSize, String... option) {
        this.page = page;
        this.pageSize = pageSize;
        this.option = List.of(option);
        }

    public String getQueryString() {
        return getQueryString(page);
    }

    public String getQueryString(Integer page) {
        new SearchHandler(1, 2, "keyword");
        new SearchHandler(1, 2, "keyword", "키워드");
        return UriComponentsBuilder.newInstance()
                .queryParam("page",     page)
                .queryParam("pageSize", pageSize)
                .queryParam("option",   option)
                .build().toString();
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = requireNonNullElse(pageSize, DEFAULT_PAGE_SIZE);

        this.pageSize = max(MIN_PAGE_SIZE, min(this.pageSize, MAX_PAGE_SIZE));
    }


    public Integer getOffset() {
        return (page-1)*pageSize;
    }

}