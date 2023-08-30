package bucheon.leafy.domain.reply.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class QnaReplyEditReqeust {

    private String comment;

    public QnaReplyEditReqeust( String comment) {
        this.comment = comment;

    }
}