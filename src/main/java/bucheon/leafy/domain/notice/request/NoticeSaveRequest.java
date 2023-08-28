package bucheon.leafy.domain.notice.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class NoticeSaveRequest {

    @JsonIgnore
    private Long noticeId;
    private String contents;
    private String title;

    public NoticeSaveRequest(Long noticeId, String contents, String title) {
        this.noticeId = noticeId;
        this.contents = contents;
        this.title = title;
    }
}