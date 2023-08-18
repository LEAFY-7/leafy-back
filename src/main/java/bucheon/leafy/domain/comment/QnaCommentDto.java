package bucheon.leafy.domain.comment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class QnaCommentDto {

    private Long qnaCommentId;
    private Date createdAt;
    private Date modifiedAt;
    private Boolean isDelete;
    private String comment;
    private Long userId;
    private Long qnaId;

    public QnaCommentDto(Long qnaCommentId, String comment, Long userId) {
        this.qnaCommentId = qnaCommentId;
        this.comment = comment;
        this.userId = userId;
    }
    public QnaCommentDto(String comment, Long userId, Long qnaId) {
        this.comment = comment;
        this.userId = userId;
        this.qnaId = qnaId;

    }

}
