package bucheon.leafy.domain.comment.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class QnaCommentEditResponse {

    private String modified_at;
    public QnaCommentEditResponse(String comment) {
        this.modified_at = modified_at;
    }
}
