package bucheon.leafy.domain.reply;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Date;


@Data
@NoArgsConstructor
@EqualsAndHashCode
public class ReplyDto {

    private Long id;
    private Date createdAt;
    private Date modifiedAt;
    private boolean isDelete;
    private String comment;
    private Long userId;
    private Long qnaCommentId;


    public ReplyDto( String comment, Long userId, Long qnaCommentId) {
        this.comment = comment;
        this.userId = userId;
        this.qnaCommentId = qnaCommentId;

    }
}