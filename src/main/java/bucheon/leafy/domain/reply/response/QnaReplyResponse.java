package bucheon.leafy.domain.reply.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class QnaReplyResponse {

    private String comment;
    private Long userId;

    public QnaReplyResponse(Long qnaReplyId, String comment, Long userId) {

        this.comment = comment;
        this.userId = userId;
    }

}