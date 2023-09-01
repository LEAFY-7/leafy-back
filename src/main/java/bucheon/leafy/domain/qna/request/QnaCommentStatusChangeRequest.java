package bucheon.leafy.domain.qna.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QnaCommentStatusChangeRequest {

    private Long qnaId;

    public QnaCommentStatusChangeRequest(Long qnaId) {
        this.qnaId = qnaId;
    }
}
