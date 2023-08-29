package bucheon.leafy.domain.qna.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@NoArgsConstructor
public class QnaSaveResponse {

    private Long qnaId;
    private Date createdAt;
    @Builder
    public QnaSaveResponse(Long qnaId, Date createdAt) {
        this.qnaId = qnaId;
        this.createdAt = createdAt;

    }

    public static QnaSaveResponse of(Long qnaId, Date createdAt) {
        return QnaSaveResponse.builder()
                .qnaId(qnaId)
                .createdAt(createdAt)
                .build();
    }

}
