package bucheon.leafy.domain.comment.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class QnaCommentResponse {

    private Long qnaCommentId;
    private LocalDateTime  createdAt;
    private LocalDateTime modifiedAt;
    private String comment;
    private Long userId;
    private Long qnaId;

    public QnaCommentResponse(Long qnaCommentId, LocalDateTime  createdAt, LocalDateTime  modifiedAt, String comment, Long userId, Long qnaId) {
        this.qnaCommentId = qnaCommentId;
        this.createdAt = createdAt;
        this.modifiedAt =modifiedAt;
        this.comment = comment;
        this.userId = userId;
        this.qnaId = qnaId;
    }


}
