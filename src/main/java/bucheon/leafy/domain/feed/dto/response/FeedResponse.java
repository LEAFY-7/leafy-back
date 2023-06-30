package bucheon.leafy.domain.feed.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FeedResponse {
    private Long feed_id;
    private Long userId;
    private String title;
    private String content;
    private Boolean is_hide;
    private Boolean isDelete;
    private Date modified_at;

    @Builder
    private FeedResponse(Long feed_id, Long userId, String title, String content, Boolean is_hide, Boolean isDelete, Date modified_at) {
        this.feed_id = feed_id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.is_hide = is_hide;
        this.isDelete = isDelete;
        this.modified_at = modified_at;
    }
}
