package bucheon.leafy.domain.qna.response;  // 패키지 경로가 맞는지 확인해주세요.

import bucheon.leafy.domain.comment.response.QnaCommentResponse;
import bucheon.leafy.domain.reply.response.QnaCommentReplyResponse;
import bucheon.leafy.domain.reply.response.QnaReplyResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class QnaResponse {
    private Long qnaId;
    private LocalDateTime createdAt;
    private LocalDateTime  modifiedAt;
    private Boolean isDelete;
    private String contents;
    private String title;
    private Long viewCount;
    private Long userId;
    private List<QnaCommentResponse> comments;
    private List<QnaReplyResponse> replies;

    public QnaResponse(Long qnaId, LocalDateTime createdAt, LocalDateTime modifiedAt, Boolean isDelete, String contents, String title, Long viewCount, Long userId, List<QnaCommentResponse> comments, List<QnaReplyResponse> replies) {
        this.qnaId = qnaId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.isDelete = isDelete;
        this.contents = contents;
        this.title = title;
        this.viewCount = viewCount;
        this.userId = userId;
        this.comments = comments;
        this.replies = replies;

    }
}
