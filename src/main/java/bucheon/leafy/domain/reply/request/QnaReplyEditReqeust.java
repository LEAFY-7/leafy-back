package bucheon.leafy.domain.reply.request;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class QnaReplyEditReqeust {

    private Long qnaReplyId;
    private String comment;
    private Long userId;


    public QnaReplyEditReqeust(Long qnaReplyId, String comment, Long userId) {
        this.qnaReplyId = qnaReplyId;
        this.comment = comment;
        this.userId = userId;
    }
}