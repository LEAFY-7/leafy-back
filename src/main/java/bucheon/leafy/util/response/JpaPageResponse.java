package bucheon.leafy.util.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@NoArgsConstructor
public class JpaPageResponse<T> {

    private List<T> body;

    private Long total;

    private Integer totalPage;

    private boolean isFirst;

    private boolean isLast;

    @Builder
    public JpaPageResponse(List<T> body, Long total,
                           Integer totalPage, boolean isFirst, boolean isLast) {

        this.body = body;
        this.total = total;
        this.totalPage = totalPage;
        this.isFirst = isFirst;
        this.isLast = isLast;
    }

    public static <T> JpaPageResponse of(List<T> body, Pageable pageable, Long total){
        if (total == 0){
            return JpaPageResponse.<T>builder()
                    .body(body)
                    .total(total)
                    .totalPage(1)
                    .isFirst(true)
                    .isLast(true)
                    .build();
        } else {
            Integer totalPage = (int) (total / pageable.getPageSize()) + 1;
            int currentPage = pageable.getPageNumber() + 1;

            boolean isFirst = currentPage == 1;
            boolean isLast = currentPage == totalPage;

            return JpaPageResponse.<T>builder()
                    .body(body)
                    .total(total)
                    .totalPage(totalPage)
                    .isFirst(isFirst)
                    .isLast(isLast)
                    .build();
        }
    }

}
