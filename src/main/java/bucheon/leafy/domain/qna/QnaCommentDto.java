package bucheon.leafy.domain.qna;

import lombok.Data;

import java.util.Date;
@Data
public class QnaCommentDto {

    private Integer id;
    private Date created_at;
    private Date  modified_at;
    private Integer isDelete;
    private String comment;
    private Integer userId;
    private Integer notice_comment_id;


    public QnaCommentDto() {}

    public QnaCommentDto(Integer id, Integer userId, String comment) {

        this.id = id;
        this.userId = userId;
        this.comment = comment;
    }
}