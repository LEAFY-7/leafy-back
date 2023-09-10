package bucheon.leafy.domain.qna.response;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QnaAlamResponse {

    private long qnaCommentId;
    private long qnaReplyId;

    public QnaAlamResponse(long qnaCommentId, long qnaReplyId){
        this.qnaCommentId = qnaCommentId;
        this.qnaReplyId = qnaReplyId;
    }
}
