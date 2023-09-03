package bucheon.leafy.domain.qna.reply.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class QnaReplyResponse {

    private Long qnaReplyId;
    private LocalDateTime  createdAt;
    private LocalDateTime modifiedAt;
    private String comment;
    private Long userId;
    private Long qnaCommentId;

    public QnaReplyResponse(Long qnaReplyId, LocalDateTime  createdAt, LocalDateTime  modifiedAt, String comment, Long userId, Long qnaCommentId) {
        this.qnaReplyId = qnaReplyId;
        this.createdAt = createdAt;
        this.modifiedAt =modifiedAt;
        this.comment = comment;
        this.userId = userId;
        this.qnaCommentId = qnaCommentId;
    }
}
