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
    private boolean isDelete;
    private String comment;
    private Long userId;
    private Long qnaId;


    public CommentDto(Long id, boolean isDelete, String comment, Long userId, Long qnaId) {

        this.id = id;
        this.isDelete = isDelete;
        this.comment = comment;
        this.userId = userId;
        this.qnaId = qnaId;

    }

}
