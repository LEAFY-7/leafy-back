package bucheon.leafy.domain.notice.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NoticeSaveResponse {
    private Long noticeId;
    private LocalDateTime createAt;

    @Builder
    public NoticeSaveResponse(Long noticeId, LocalDateTime createAt) {
        this.noticeId = noticeId;
        this.createAt = createAt;
    }

    public static NoticeSaveResponse of(Long noticeId, LocalDateTime createAt) {
        return NoticeSaveResponse.builder()
                .noticeId(noticeId)
                .createAt(createAt)
                .build();
    }


}
