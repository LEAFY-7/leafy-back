package bucheon.leafy.domain.comment.response;

import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@NoArgsConstructor
public class QnaCommentEditResponse {

    private Date modifiedAt;
    public QnaCommentEditResponse(Date modifiedAt) {

        this.modifiedAt = modifiedAt;
    }
}
