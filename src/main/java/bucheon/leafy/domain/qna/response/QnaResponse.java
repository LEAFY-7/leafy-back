package bucheon.leafy.domain.qna.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class QnaResponse {
    private Long qnaId;
    private Date createAt;
    private Date modifiedAt;
    private Boolean isDelete;
    private String contents;
    private String title;
    private Long viewCount;
    private Long userId;

    public QnaResponse(Long qnaId,Date createAt,Date modifiedAt, Boolean isDelete, String contents, String title, Long viewCount, Long userId) {

        this.qnaId = qnaId;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
        this.isDelete = isDelete;
        this.contents =contents;
        this.title = title;
        this.viewCount =viewCount;
        this.userId = userId;
    }
}
