package bucheon.leafy.domain.qna.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@NoArgsConstructor
public class QnaSaveResponse {
    private Long qnaId;
    private Date createAt;
    @Builder
    public QnaSaveResponse(Long qnaId, Date createAt) {
        this.qnaId = qnaId;
        this.createAt = createAt;

    }

    public static QnaSaveResponse of(Long qnaId, Date createdAt) {
        return QnaSaveResponse.builder()
                .qnaId(qnaId)
                .createAt(createdAt)
                .build();
    }

}
