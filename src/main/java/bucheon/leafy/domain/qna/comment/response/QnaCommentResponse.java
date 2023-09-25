package bucheon.leafy.domain.qna.comment.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class QnaCommentResponse {
    @JsonProperty(index = 1, value = "id")
    private Long qnaCommentId;
    private Long userId;
    private Long qnaId;
    private String comment;
    private Date createdAt;
    private Date modifiedAt;


    public QnaCommentResponse(Long qnaCommentId, Long qnaId, String comment, Long userId, Date createdAt, Date  modifiedAt) {
        this.qnaCommentId = qnaCommentId;
        this.userId = userId;
        this.qnaId = qnaId;
        this.comment = comment;
        this.createdAt = createdAt;
        this.modifiedAt =modifiedAt;
    }
}
