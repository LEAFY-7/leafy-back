package bucheon.leafy.domain.comment.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class QnaCommentSaveResponse {
    @JsonIgnore
    private Long qnaId;
    private Long qnaCommentId;
    private Date createAt;

    public QnaCommentSaveResponse(Long qnaId, Long qnaCommentId, Date createAt) {
        this.qnaId = qnaId;
        this.qnaCommentId = qnaCommentId;
        this.createAt = createAt;

    }
}
