package bucheon.leafy.domain.reply.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class QnaReplyReqeust {

    private Long qnaReplyId;
    private String comment;
    private Long userId;
    private Long qnaCommentId;


    public QnaReplyReqeust(Long qnaReplyId, String comment, Long userId) {
        this.qnaReplyId = qnaReplyId;
        this.comment = comment;
        this.userId = userId;
    }
    public QnaReplyReqeust(String comment, Long userId, Long qnaCommentId) {
        this.comment = comment;
        this.userId = userId;
        this.qnaCommentId = qnaCommentId;
    }
}