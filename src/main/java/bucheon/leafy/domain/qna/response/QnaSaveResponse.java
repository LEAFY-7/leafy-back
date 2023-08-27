package bucheon.leafy.domain.qna.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class QnaSaveResponse {
    private Long qnaId;
    private Date createAt;

    public QnaSaveResponse(Long qnaId, Date createAt) {
        this.qnaId = qnaId;
        this.createAt = createAt;

    }

}
