package bucheon.leafy.util.response;

import bucheon.leafy.util.request.PageRequest;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PageResponse <T> {

    private PageRequest pageRequest;

    private List<T> body;

    private Long total;

    private Integer totalPage;

    private boolean isFirst;

    private boolean isLast;

    @Builder
    public PageResponse(PageRequest pageRequest, List<T> body, Long total,
                        Integer totalPage, boolean isFirst, boolean isLast) {

        this.pageRequest = pageRequest;
        this.body = body;
        this.total = total;
        this.totalPage = totalPage;
        this.isFirst = isFirst;
        this.isLast = isLast;
    }

    public static <T> PageResponse of(PageRequest pageRequest, List<T> body, Long total){

        Integer totalPage = (int) (total / Long.valueOf(pageRequest.limit));
        totalPage = total % Long.valueOf(pageRequest.limit) == 0 ? totalPage : totalPage + 1;
        boolean isFirst = pageRequest.getPage() == 1;
        boolean isLast = pageRequest.getPage() == totalPage;

        return PageResponse.<T>builder()
                .pageRequest(pageRequest)
                .body(body)
                .total(total)
                .totalPage(totalPage.intValue())
                .isFirst(isFirst)
                .isLast(isLast)
                .build();
    }



}