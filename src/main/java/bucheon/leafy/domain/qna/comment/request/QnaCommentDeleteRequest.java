package bucheon.leafy.domain.qna.comment.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QnaCommentDeleteRequest {

    private Long qnaCommentId;
    private Long userId;

    public QnaCommentDeleteRequest(Long qnaCommentId, Long userId) {
        this.qnaCommentId = qnaCommentId;
        this.userId = userId;
    }


}
