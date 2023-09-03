package bucheon.leafy.domain.qna.comment.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QnaCommentSaveRequest {

    @JsonIgnore
    private Long qnaCommentId;
    private String comment;

}
