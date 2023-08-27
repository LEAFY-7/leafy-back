package bucheon.leafy.util.response;

import bucheon.leafy.util.request.PageRequest;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PageResponse <T> {

    private List<T> body;

    private Long total;

    private Integer totalPage;


    @Builder
    public PageResponse(List<T> body, Long total,
                        Integer totalPage) {

        this.body = body;
        this.total = total;
        this.totalPage = totalPage;
    }

    public static <T> PageResponse of(PageRequest pageRequest, List<T> body, Long total){
        if (total == 0){
            return PageResponse.<T>builder()
                    .body(body)
                    .total(total)
                    .totalPage(1)
                    .build();
        } else {
            Integer totalPage = (int) (total / Long.valueOf(pageRequest.limit));
            totalPage = total % Long.valueOf(pageRequest.limit) == 0 ? totalPage : totalPage + 1;

            return PageResponse.<T>builder()
                    .body(body)
                    .total(total)
                    .totalPage(totalPage.intValue())
                    .build();
        }
    }

}
