package bucheon.leafy.domain.qna;

import lombok.Data;

import java.util.Date;
@Data
public class QnaCommentDto {

    private Integer id;
    private Date created_at;
    private Date  modified_at;
    private Integer is_delete;
    private String comment;
    private Integer user_id;
    private Integer notice_comment_id;


    public QnaCommentDto() {}

    public QnaCommentDto(Integer id, Integer user_id, String comment) {

        this.id = id;
        this.user_id = user_id;
        this.comment = comment;
    }
}