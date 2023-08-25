package bucheon.leafy.domain.notice.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeRequest {
    private Long noticeId;

    public NoticeRequest(Long noticeId){
        this.noticeId = noticeId;

    }

}
