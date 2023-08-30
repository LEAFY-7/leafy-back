package bucheon.leafy.domain.reply.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class QnaReplySaveResponse {

    private Long qnaReplyId;
    private Date createdAt;


    public QnaReplySaveResponse(Long qnaReplyId, Date createdAt) {
        this.qnaReplyId = qnaReplyId;
        this.createdAt = createdAt;
    }
}