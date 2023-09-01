package bucheon.leafy.domain.qna.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class QnaEditResponse {
    private LocalDateTime  modifiedAt;
    public QnaEditResponse(LocalDateTime  modifiedAt){
        this.modifiedAt = modifiedAt;

    }
}
