package bucheon.leafy.domain.comment.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QnaCommentSaveReqeust {

    private String comment;
    private Long userId;
    private Long qnaId;

    public QnaCommentSaveReqeust(String comment, Long userId, Long qnaId) {
        this.comment = comment;
        this.userId = userId;
        this.qnaId = qnaId;

    }

}
