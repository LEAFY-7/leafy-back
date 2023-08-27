package bucheon.leafy.domain.notice.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class NoticeSaveResponse {
    private Long noticeId;
    private Date createAt;

    public NoticeSaveResponse(Long noticeId, Date createAt) {
        this.noticeId = noticeId;
        this.createAt = createAt;

    }







































































}
