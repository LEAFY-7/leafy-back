package bucheon.leafy.domain.comment.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class QnaCommentEditResponse {

    private LocalDateTime modified_at;
    public QnaCommentEditResponse(LocalDateTime modified_at) {
        this.modified_at = modified_at;
    }
}
