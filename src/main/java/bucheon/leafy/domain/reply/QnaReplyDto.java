package bucheon.leafy.domain.reply;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@EqualsAndHashCode
public class QnaReplyDto {

    private Long qnaReplyId;
    private String comment;
    private Long userId;
    private Long qnaCommentId;


    public QnaReplyDto(Long qnaReplyId, String comment, Long userId) {
        this.qnaReplyId = qnaReplyId;
        this.comment = comment;
        this.userId = userId;
    }
    public QnaReplyDto( String comment, Long userId, Long qnaCommentId) {
        this.comment = comment;
        this.userId = userId;
        this.qnaCommentId = qnaCommentId;
    }
}