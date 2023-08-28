package bucheon.leafy.domain.notice.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeUserRequest {
    private Long userId;

    public NoticeUserRequest(Long userId){
        this.userId = userId;

    }

}
