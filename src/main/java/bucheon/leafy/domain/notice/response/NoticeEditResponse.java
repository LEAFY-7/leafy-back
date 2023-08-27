package bucheon.leafy.domain.notice.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class NoticeEditResponse {
    private Date modifiedAt;

    @Builder
    public NoticeEditResponse(Date modifiedAt){
        this.modifiedAt = modifiedAt;

    }
    public static NoticeEditResponse of(Date modifiedAt) {
        return NoticeEditResponse.builder()
                .modifiedAt(modifiedAt)
                .build();
    }

}
