package bucheon.leafy.domain.qna.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class QnaSaveResponse {
    private Long qnaId;
    private LocalDateTime createdAt;
    @Builder
    public QnaSaveResponse(Long qnaId, LocalDateTime createdAt) {
        this.qnaId = qnaId;
        this.createdAt = createdAt;

    }

    public static QnaSaveResponse of(Long qnaId, LocalDateTime createdAt) {
        return QnaSaveResponse.builder()
                .qnaId(qnaId)
                .createdAt(createdAt)
                .build();
    }

}
