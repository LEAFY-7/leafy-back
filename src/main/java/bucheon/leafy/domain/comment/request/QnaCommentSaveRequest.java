package bucheon.leafy.domain.comment.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QnaCommentSaveRequest {  // 클래스 이름의 오타를 수정했습니다

    @JsonIgnore
    private Long qnaCommentId;
    private String comment;
    private Long qnaId;

    public QnaCommentSaveRequest(Long qnaCommentId, String comment, Long qnaId) {
        this.qnaCommentId = qnaCommentId;
        this.comment = comment;
        this.qnaId = qnaId;
    }
}
