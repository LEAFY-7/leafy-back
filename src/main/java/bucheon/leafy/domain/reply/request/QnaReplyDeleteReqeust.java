package bucheon.leafy.domain.reply.request;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class QnaReplyDeleteReqeust {
    private Long qnaReplyId;
    private Long userId;

    public QnaReplyDeleteReqeust(Long qnaReplyId, Long userId) {
        this.qnaReplyId = qnaReplyId;
        this.userId = userId;
    }
}