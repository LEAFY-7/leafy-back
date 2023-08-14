package bucheon.leafy.util.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeSearchRequest {
    private String option;
    private String keyword;

    public NoticeSearchRequest(String option, String keyword) {
        this.option = option;
        this.keyword = keyword;
    }
}