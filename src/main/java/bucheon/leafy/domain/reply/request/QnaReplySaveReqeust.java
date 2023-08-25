package bucheon.leafy.domain.reply.request;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class QnaReplySaveReqeust {

    private String comment;
    private Long userId;
    private Long qnaCommentId;

    public QnaReplySaveReqeust(String comment, Long userId, Long qnaCommentId) {
        this.comment = comment;
        this.userId = userId;
        this.qnaCommentId = qnaCommentId;
    }
}