package bucheon.leafy.domain.qna.reply.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@NoArgsConstructor
public class QnaReplySaveResponse {

    private Long qnaReplyId;
    private LocalDateTime createdAt;


    public QnaReplySaveResponse(Long qnaReplyId, LocalDateTime createdAt) {
        this.qnaReplyId = qnaReplyId;
        this.createdAt = createdAt;
    }
}