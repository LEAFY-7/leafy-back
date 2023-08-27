package bucheon.leafy.domain.reply.request;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class QnaReplyEditReqeust {

    private String comment;

    public QnaReplyEditReqeust( String comment) {
        this.comment = comment;

    }
}