package bucheon.leafy.domain.qna.response;

import bucheon.leafy.domain.qna.QnaStatus;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class QnaStatusResponse {

    private QnaStatus qnaStatus;

    public QnaStatusResponse(QnaStatus qnaStatus ) {
        this.qnaStatus = qnaStatus == null ?  QnaStatus.HOLD : QnaStatus.DONE;
    }
}
