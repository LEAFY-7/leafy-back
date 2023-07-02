package bucheon.leafy.util.response;

import bucheon.leafy.util.request.ScrollRequest;
 import lombok.Data;
 import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ScrollResponse <T> {

    private ScrollRequest scrollRequest;

    private List<T> body;

    public ScrollResponse(ScrollRequest scrollRequest, List<T> body) {
        this.scrollRequest = scrollRequest;
        this.body = body;
    }

}
