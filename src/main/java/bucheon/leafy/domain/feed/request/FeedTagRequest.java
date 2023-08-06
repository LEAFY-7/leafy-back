package bucheon.leafy.domain.feed.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FeedTagRequest {

    @JsonIgnore
    private Long feedTagId;
    private String tag;

    @Builder
    private FeedTagRequest(String tag) {
        this.tag = tag;
    }
}
