package bucheon.leafy.domain.qna.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class QnaSaveResponse {
    private Date createAt;

    public QnaSaveResponse( Date createAt) {
        this.createAt = createAt;

    }

}
