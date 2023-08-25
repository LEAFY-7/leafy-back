package bucheon.leafy.domain.qna.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class QnaStatusResponse {

    private String qnaStatus;

    public QnaStatusResponse(String qnaStatus ) {
    }
}
