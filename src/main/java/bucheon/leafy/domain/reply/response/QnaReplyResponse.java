package bucheon.leafy.domain.reply.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class QnaReplyResponse {

    private Long qnaReplyId;
    private String comment;
    private Long userId;
    private Long qnaCommentId;


    public QnaReplyResponse(Long qnaReplyId, String comment, Long userId) {
        this.qnaReplyId = qnaReplyId;
        this.comment = comment;
        this.userId = userId;
    }
    public QnaReplyResponse(String comment, Long userId, Long qnaCommentId) {
        this.comment = comment;
        this.userId = userId;
        this.qnaCommentId = qnaCommentId;
    }
}