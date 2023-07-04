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

    @Builder
    private PageResponse(PageRequest pageRequest, List<T> body) {
        this.pageRequest = pageRequest;
        this.body = body;
    }

    public static <T> PageResponse of(PageRequest pageRequest, List<T> body){
        return PageResponse.<T>builder()
                .pageRequest(pageRequest)
                .body(body)
                .build();
    }

}
