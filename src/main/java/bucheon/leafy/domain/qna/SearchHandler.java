package bucheon.leafy.domain.qna;

import lombok.*;
import org.springframework.web.util.UriComponentsBuilder;
<<<<<<< HEAD
import static java.lang.Math.*;
import static java.util.Objects.requireNonNullElse;

=======

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;
import static java.util.Objects.requireNonNullElse;
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
@Data
@NoArgsConstructor
public class SearchHandler {
    private Integer page = 1;
    private Integer pageSize = DEFAULT_PAGE_SIZE;
<<<<<<< HEAD
    private String  option = "";
    private String  keyword = "";
=======
    private List<String> option = new ArrayList<>();
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
    public static final int MIN_PAGE_SIZE = 5;
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int MAX_PAGE_SIZE = 50;

<<<<<<< HEAD

=======
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
    public SearchHandler(Integer page, Integer pageSize) {
        this(page, pageSize, "", "");
    }

<<<<<<< HEAD
    public SearchHandler(Integer page, Integer pageSize, String option, String keyword) {
        this.page = page;
        this.pageSize = pageSize;
        this.option = option;
        this.keyword = keyword;
    }

    public String getQueryString() {
        return getQueryString(page);
    }   //Qna게시판 자체를 클릭해서 프론트쪽에서 page파라미터를 넘겨줄일이 없을때 getQueryString을 호출하고 전역변수 page값 1을 넘겨줌

    public String getQueryString(Integer page) {
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
=======
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
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
                .build().toString();
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = requireNonNullElse(pageSize, DEFAULT_PAGE_SIZE);
<<<<<<< HEAD
        this.pageSize = max(MIN_PAGE_SIZE, min(this.pageSize, MAX_PAGE_SIZE));
    }

=======

        this.pageSize = max(MIN_PAGE_SIZE, min(this.pageSize, MAX_PAGE_SIZE));
    }


>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
    public Integer getOffset() {
        return (page-1)*pageSize;
    }

<<<<<<< HEAD

=======
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
}