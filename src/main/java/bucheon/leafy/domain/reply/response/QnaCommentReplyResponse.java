package bucheon.leafy.domain.reply.response;

import bucheon.leafy.domain.comment.response.QnaCommentResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class QnaCommentReplyResponse {
    List<QnaCommentResponse> comments;
    List<QnaReplyResponse> replies;
}
