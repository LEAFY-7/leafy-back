package bucheon.leafy.domain.qna.comment.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class QnaCommentSaveResponse {
    @JsonIgnore
    private Long qnaId;
    private Long qnaCommentId;
    private LocalDateTime  createdAt;

    public QnaCommentSaveResponse(Long qnaId, Long qnaCommentId, LocalDateTime createdAt) {
        this.qnaId = qnaId;
        this.qnaCommentId = qnaCommentId;
        this.createdAt = createdAt;

    }
}
