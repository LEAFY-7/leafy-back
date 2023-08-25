package bucheon.leafy.domain.notice.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeResponse {
    private Long noticeId;
    private String contents;
    private String title;
    private Long userId;

    public NoticeResponse(String contents, String title, Long userId) {
        this.contents = contents;
        this.title = title;
        this.userId = userId;
    }

    public NoticeResponse(Long noticeId, String contents, String title){
        this.noticeId = noticeId;
        this.contents = contents;
        this.title = title;
    }


}
