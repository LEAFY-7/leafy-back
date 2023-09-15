package bucheon.leafy.domain.qna.response;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QnaAlamResponse {

    private Long qnaCommentIds;
    private Long qnaReplyIds;

    public QnaAlamResponse(Long qnaCommentIds, Long qnaReplyIds) {
        this.qnaCommentIds = qnaCommentIds;
        this.qnaReplyIds = qnaReplyIds;
    }
}
