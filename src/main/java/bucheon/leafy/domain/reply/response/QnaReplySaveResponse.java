package bucheon.leafy.domain.reply.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class QnaReplySaveResponse {

    private Long qnaReplyId;
    private Date createAt;


    public QnaReplySaveResponse(Long qnaReplyId, Date createAt) {
        this.qnaReplyId = qnaReplyId;
        this.createAt = createAt;
    }
}