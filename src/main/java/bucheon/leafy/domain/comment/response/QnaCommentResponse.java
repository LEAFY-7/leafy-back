package bucheon.leafy.domain.comment.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QnaCommentResponse {

    private Long qnaCommentId;
    private String comment;
    private Long userId;
    private Long qnaId;

    public QnaCommentResponse(Long qnaCommentId, String comment, Long userId) {
        this.qnaCommentId = qnaCommentId;
        this.comment = comment;
        this.userId = userId;
    }
    public QnaCommentResponse(String comment, Long userId, Long qnaId) {
        this.comment = comment;
        this.userId = userId;
        this.qnaId = qnaId;

    }

}
