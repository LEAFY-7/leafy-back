package bucheon.leafy.domain.qna.response;  // 패키지 경로가 맞는지 확인해주세요.

import bucheon.leafy.domain.reply.response.QnaReplyResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;
import bucheon.leafy.domain.comment.response.QnaCommentResponse;  // 패키지 경로가 맞는지 확인해주세요.

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
    private List<QnaCommentResponse> comment;  // QnaCommentResponse 객체의 리스트
    private List<QnaReplyResponse> qnaReply;

    public QnaResponse(Long qnaId, Date createAt, Date modifiedAt, Boolean isDelete, String contents, String title, Long viewCount, Long userId, List<QnaCommentResponse> comment, List<QnaReplyResponse> qnaReply) {
        this.qnaId = qnaId;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
        this.isDelete = isDelete;
        this.contents = contents;
        this.title = title;
        this.viewCount = viewCount;
        this.userId = userId;
        this.comment = comment;
        this.qnaReply = qnaReply;
    }
}
