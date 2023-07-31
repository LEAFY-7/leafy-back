package bucheon.leafy.util.response;

import bucheon.leafy.util.request.ScrollRequest;
import lombok.Builder;
import lombok.Data;
 import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ScrollResponse <T> {

    private ScrollRequest scrollRequest;

    private List<T> body;

    @Builder
    private ScrollResponse(ScrollRequest scrollRequest, List<T> body) {
        this.scrollRequest = scrollRequest;
        this.body = body;
    }

    public static <T> ScrollResponse of(ScrollRequest scrollRequest, List<T> body) {
        return ScrollResponse.<T>builder()
                .scrollRequest(scrollRequest)
                .body(body)
                .build();
    }

}
