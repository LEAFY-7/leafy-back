package bucheon.leafy.domain.comment.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QnaCommentEditReqeust {

    private Long qnaCommentId;
    private String comment;
    private Long userId;

    public QnaCommentEditReqeust(Long qnaCommentId, String comment, Long userId) {
        this.qnaCommentId = qnaCommentId;
        this.comment = comment;
        this.userId = userId;
    }

}
