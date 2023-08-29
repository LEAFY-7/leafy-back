package bucheon.leafy.domain.comment.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class QnaCommentEditResponse {

    private String comment;
    public QnaCommentEditResponse(String comment) {
        this.comment = comment;
    }
}
