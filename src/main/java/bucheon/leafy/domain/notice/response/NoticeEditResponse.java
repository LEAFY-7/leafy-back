package bucheon.leafy.domain.notice.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NoticeEditResponse {
    private LocalDateTime modifiedAt;

    @Builder
    public NoticeEditResponse(LocalDateTime modifiedAt){
        this.modifiedAt = modifiedAt;

    }
    public static NoticeEditResponse of(LocalDateTime modifiedAt) {
        return NoticeEditResponse.builder()
                .modifiedAt(modifiedAt)
                .build();
    }

}
