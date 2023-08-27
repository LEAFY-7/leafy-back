package bucheon.leafy.domain.qna.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QnaRequest {
    private Long qnaId;

    public QnaRequest( Long qnaId) {
        this.qnaId = qnaId;
    }
}
