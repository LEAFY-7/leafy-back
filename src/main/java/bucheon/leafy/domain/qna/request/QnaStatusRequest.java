package bucheon.leafy.domain.qna.request;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QnaStatusRequest {

    private Long qnaId;
    public QnaStatusRequest( Long qnaId) {
        this.qnaId = qnaId;
    }
}
