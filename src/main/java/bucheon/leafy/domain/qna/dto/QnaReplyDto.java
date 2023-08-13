package bucheon.leafy.domain.qna.dto;
import lombok.*;

import java.util.Date;

@Data
public class QnaReplyDto {
    private Long id;
    private Date  createdAt;
    private Date  modifiedAt;
    private boolean isDelete;
    private String comment;
    private Long userId;
    private Long qnaCommentId;


    public QnaReplyDto(String comment, Long userId, Long qnaCommentId) {
        this.comment = comment;
        this.userId = userId;
        this.qnaCommentId = qnaCommentId;
    }
}