package bucheon.leafy.domain.notice.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class NoticeSaveResponse {
    private Long noticeId;
    private Date createAt;

    @Builder
    public NoticeSaveResponse(Long noticeId, Date createAt) {
        this.noticeId = noticeId;
        this.createAt = createAt;
    }

    public static NoticeSaveResponse of(Long noticeId, Date createAt) {
        return NoticeSaveResponse.builder()
                .noticeId(noticeId)
                .createAt(createAt)
                .build();
    }


}
