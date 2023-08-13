package bucheon.leafy.util.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchRequest {
    private String option;
    private String keyword;

    public SearchRequest(String option, String keyword) {
        this.option = option;
        this.keyword = keyword;
    }
}