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
<<<<<<< HEAD
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

=======
    private boolean isDelete;
    private String comment;
    private Long userId;
    private Long qnaId;


    public CommentDto(Long id, boolean isDelete, String comment, Long userId, Long qnaId) {

        this.id = id;
        this.isDelete = isDelete;
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
        this.comment = comment;
        this.userId = userId;
        this.qnaId = qnaId;

    }

}
