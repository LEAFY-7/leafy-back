package bucheon.leafy.domain.qna;

import lombok.Data;

import java.util.Date;
@Data
public class QnaCommentDto {

    private Long id;
    private Date created_at;
    private Date  modified_at;
    private Integer is_delete;
    private String comment;
    private Integer user_user_id;
    private Integer notice_comment_id;


    public QnaCommentDto() {}

    public QnaCommentDto(Long id, Integer user_user_id, String comment) {

        this.id = id;
        this.user_user_id = user_user_id;
        this.comment = comment;
    }
}