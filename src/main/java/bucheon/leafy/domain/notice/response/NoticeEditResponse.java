package bucheon.leafy.domain.notice.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class NoticeEditResponse {
    private Date modifiedAt;
    public NoticeEditResponse(Date modifiedAt){
        this.modifiedAt = modifiedAt;

    }
}
