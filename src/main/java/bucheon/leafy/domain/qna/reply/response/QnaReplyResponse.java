package bucheon.leafy.domain.qna.reply.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class QnaReplyResponse {
    @JsonProperty(index = 1, value = "id")
    private Long qnaReplyId;
    private Long userId;
    private Long qnaCommentId;
    @JsonProperty(index = 4, value = "reply")
    private String comment;
    private Date  createdAt;
    private Date modifiedAt;

    public QnaReplyResponse(Long qnaReplyId, Date createdAt, Date  modifiedAt, String comment, Long userId, Long qnaCommentId) {
        this.qnaReplyId = qnaReplyId;
        this.userId = userId;
        this.qnaCommentId = qnaCommentId;
        this.comment = comment;
        this.createdAt = createdAt;
        this.modifiedAt =modifiedAt;
    }
}
