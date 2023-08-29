package bucheon.leafy.domain.reply.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QnaReplyResponse {

    private Long qnaReplyId;
    private Long createdAt;
    private Long modifiedAt;
    private String comment;
    private Long userId;
    private Long qnaCommentId;

    public QnaReplyResponse(Long qnaReplyId, Long createdAt, Long modifiedAt, String comment, Long userId, Long qnaCommentId) {
        this.qnaReplyId = qnaReplyId;
        this.createdAt = createdAt;
        this.modifiedAt =modifiedAt;
        this.comment = comment;
        this.userId = userId;
        this.qnaCommentId = qnaCommentId;
    }


}
