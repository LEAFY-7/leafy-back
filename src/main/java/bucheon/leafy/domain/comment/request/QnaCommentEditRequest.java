package bucheon.leafy.domain.comment.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class QnaCommentEditRequest {

    private Date comment;

    public QnaCommentEditRequest( Date comment) {
        this.comment = comment;
    }

}
