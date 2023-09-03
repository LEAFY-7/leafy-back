package bucheon.leafy.domain.qna.reply.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@NoArgsConstructor
public class QnaReplyEditResponse {

    private LocalDateTime modifiedAt;

    public QnaReplyEditResponse( LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}