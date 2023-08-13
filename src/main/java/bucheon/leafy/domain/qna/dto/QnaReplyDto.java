package bucheon.leafy.domain.qna.dto;
import lombok.*;

import java.util.Date;

@Data
public class QnaReplyDto {
    private Long id;
    private Date  created_at;
    private Date  modified_at;
    private Integer is_delete;
    private String comment;
    private Long user_user_id;
    private Long notice_comment_id;


    public QnaReplyDto(Long id, Long user_user_id, String comment) {

        this.id = id;
        this.user_user_id = user_user_id;
        this.comment = comment;
    }
}