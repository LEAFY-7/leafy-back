package bucheon.leafy.domain.qna.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class QnaEditResponse {
    private Date modifiedAt;
    public QnaEditResponse(Date modifiedAt){
        this.modifiedAt = modifiedAt;

    }
}
