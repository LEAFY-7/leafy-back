package bucheon.leafy.domain.feed.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FeedTagRequest {

    private Long tagId;
    private String tag;

    @Builder
    private FeedTagRequest(Long tagId, String tag) {
        this.tagId = tagId;
        this.tag = tag;
    }
}
