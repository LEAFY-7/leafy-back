package bucheon.leafy.domain.notice.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class NoticeResponse {
    private Long noticeId;
    private String contents;
    private String title;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long viewCount;


    public NoticeResponse(Long noticeId, String contents, String title, Long userId,
                          LocalDateTime createdAt, LocalDateTime modifiedAt, Long viewCount) {
        this.noticeId = noticeId;
        this.contents = contents;
        this.title = title;
        this.userId = userId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.viewCount = viewCount;
    }
}
