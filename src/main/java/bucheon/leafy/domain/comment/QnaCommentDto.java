package bucheon.leafy.domain.comment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class QnaCommentDto {

    private Long id;
    private Date createdAt;
    private Date modifiedAt;
    private String comment;
    private Long userId;
    private Long qnaId;
    private String answerContent;
    private String email;
    public QnaCommentDto(Long id, String comment, Long userId) {
        this.comment = comment;
        this.id = id;
        this.userId = userId;
    }

    public QnaCommentDto(String comment, Long userId, Long qnaId) {

        this.comment = comment;
        this.userId = userId;
        this.qnaId = qnaId;

    }

}
