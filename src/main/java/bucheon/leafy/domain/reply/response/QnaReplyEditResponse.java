package bucheon.leafy.domain.reply.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class QnaReplyEditResponse {

    private Date modifiedAt;

    public QnaReplyEditResponse( Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}