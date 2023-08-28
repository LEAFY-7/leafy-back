package bucheon.leafy.domain.qna.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class QnaResponse {
    private Long qnaId;
    private Date create_at;
    private Date modified_at;
    private Boolean is_delete;
    private String contents;
    private String title;
    private Long view_count;
    private Long user_id;

    public QnaResponse(Long qnaId,Date createAt,Date modifiedAt, Boolean isDelete, String contents, String title, Long viewCount, Long userId) {

        this.qnaId = qnaId;
        this.create_at = createAt;
        this.modified_at = modifiedAt;
        this.is_delete = isDelete;
        this.contents =contents;
        this.title = title;
        this.view_count =viewCount;
        this.user_id = userId;
    }
}
