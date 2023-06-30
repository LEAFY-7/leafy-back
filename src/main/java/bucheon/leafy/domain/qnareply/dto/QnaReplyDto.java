package bucheon.leafy.domain.qnareply.dto;
import lombok.*;

import java.time.LocalTime;


@Data
@NoArgsConstructor
public class QnaReplyDto {
    private Long id;
    private LocalTime createdAt;
    private LocalTime modifiedAt;
    private Integer isDelete;
    private String comment;
    private Integer userId;
    private Long noticeCommentId;


    public QnaReplyDto(Long id, Integer userId, String comment) {

        this.id = id;
        this.userId = userId;
        this.comment = comment;
    }
}