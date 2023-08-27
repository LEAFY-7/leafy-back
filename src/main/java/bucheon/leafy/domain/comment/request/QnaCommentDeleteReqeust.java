package bucheon.leafy.domain.comment.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QnaCommentDeleteReqeust {

    private Long qnaCommentId;
    private Long userId;

    public QnaCommentDeleteReqeust(Long qnaCommentId, Long userId) {
        this.qnaCommentId = qnaCommentId;
        this.userId = userId;
    }


}
