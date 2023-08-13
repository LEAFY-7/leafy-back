package bucheon.leafy.domain.reply;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@NoArgsConstructor
@EqualsAndHashCode
public class QnaReplyDto {

    private Long id;
    private Date createdAt;
    private Date modifiedAt;
    private boolean isDelete;
    private String comment;
    private Long userId;
    private Long qnaCommentId;


    public QnaReplyDto( Long id, String comment, Long userId, Long qnaCommentId) {
        this.id = id;
        this.comment = comment;
        this.userId = userId;
        this.qnaCommentId = qnaCommentId;

    }
}