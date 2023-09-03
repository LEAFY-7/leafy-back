package bucheon.leafy.domain.qna.reply.request;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class QnaReplyEditRequest {

    private String comment;

    public QnaReplyEditRequest( String comment) {
        this.comment = comment;

    }
}