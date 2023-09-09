package bucheon.leafy.domain.qna.reply.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class QnaReplySaveRequest {
    @JsonIgnore
    private Long qnaReplyId;
    private String comment;


    public QnaReplySaveRequest(Long qnaReplyId, String comment) {
        this.qnaReplyId = qnaReplyId;
        this.comment = comment;

    }
}