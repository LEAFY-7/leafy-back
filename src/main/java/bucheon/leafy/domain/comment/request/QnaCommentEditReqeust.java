package bucheon.leafy.domain.comment.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QnaCommentEditReqeust {

    private String comment;

    public QnaCommentEditReqeust( String comment) {
        this.comment = comment;
    }

}
