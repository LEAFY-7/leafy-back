package bucheon.leafy.domain.qna.response;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QnaAlamResponse {

    private Long qnaComment;
    private Long qnaReply;

    public QnaAlamResponse(Long qnaComment, Long qnaReply) {
        this.qnaComment = qnaComment;
        this.qnaReply = qnaReply;
    }
}
