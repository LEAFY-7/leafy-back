package bucheon.leafy.domain.comment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Date;
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class CommentDto {

    private Long id;
    private Date createdAt;
    private Date modifiedAt;
    private String comment;
    private Long userId;
    private Long qnaId;
    private String answerContent;
    private String email;
    public CommentDto(Long id, String comment, Long userId) {
        this.comment = comment;
        this.id = id;
        this.userId = userId;
    }

    public CommentDto(String comment, Long userId, Long qnaId) {

        this.comment = comment;
        this.userId = userId;
        this.qnaId = qnaId;

    }

}
