package bucheon.leafy.domain.comment.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class QnaCommentSaveResponse {

    private Long qnaCommentId;
    private Date createAt;

    public QnaCommentSaveResponse(Long qnaCommentId, Date createAt) {
        this.qnaCommentId = qnaCommentId;
        this.createAt = createAt;

    }
}
