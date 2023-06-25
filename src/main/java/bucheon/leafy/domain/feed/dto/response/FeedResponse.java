package bucheon.leafy.domain.feed.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class FeedResponse {
    private Long feed_id;
    private Long user_id;
    private String title;
    private String content;
    private Boolean is_hide;
    private Boolean is_delete;
    private Date modified_at;

    @Builder
    private FeedResponse(Long feed_id, Long user_id, String title, String content, Boolean is_hide, Boolean is_delete, Date modified_at) {
        this.feed_id = feed_id;
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.is_hide = is_hide;
        this.is_delete = is_delete;
        this.modified_at = modified_at;
    }
}
