package bucheon.leafy.domain.notice.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class NoticeResponse {
    private Long noticeId;
    private String contents;
    private String title;
    private Long userId;
    private Date createdAt;
    private Date modifiedAt;
    private Long viewCount;


    public NoticeResponse(Long noticeId, String contents, String title, Long userId,
                          Date createdAt, Date modifiedAt, Long viewCount) {
        this.noticeId = noticeId;
        this.contents = contents;
        this.title = title;
        this.userId = userId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.viewCount = viewCount;
    }
}
