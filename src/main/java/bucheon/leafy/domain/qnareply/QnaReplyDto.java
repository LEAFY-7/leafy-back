package bucheon.leafy.domain.qnareply;
import lombok.*;

import java.util.Date;

@Data
public class QnaReplyDto {
    private Integer id;
    private Date  created_at;
    private Date  modified_at;
    private Integer is_delete;
    private String comment;
    private Integer user_user_id;
    private Integer notice_comment_id;


    public QnaReplyDto() {}

    public QnaReplyDto(Integer id, Integer user_user_id, String comment) {

        this.id = id;
        this.user_user_id = user_user_id;
        this.comment = comment;
    }
}