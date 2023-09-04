package bucheon.leafy.domain.qna.response;  // 패키지 경로가 맞는지 확인해주세요.

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class QnaListResponse {
    private Long qnaId;
    private LocalDateTime createdAt;
    private LocalDateTime  modifiedAt;
    private Boolean isDelete;
    private String contents;
    private String title;
    private Long viewCount;
    private Long userId;

    public QnaListResponse(Long qnaId, LocalDateTime createdAt, LocalDateTime modifiedAt, Boolean isDelete, String contents, String title, Long viewCount, Long userId) {
        this.qnaId = qnaId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.isDelete = isDelete;
        this.contents = contents;
        this.title = title;
        this.viewCount = viewCount;
        this.userId = userId;
    }
}
