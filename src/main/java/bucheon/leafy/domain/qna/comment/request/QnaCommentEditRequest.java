package bucheon.leafy.domain.qna.comment.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QnaCommentEditRequest {

    private String comment;

    public QnaCommentEditRequest( String comment) {
        this.comment = comment;
    }

}
