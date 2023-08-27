package bucheon.leafy.domain.notice.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeSaveRequest {
    private String contents;
    private String title;

    public NoticeSaveRequest(String contents, String title) {
        this.contents = contents;
        this.title = title;
    }







































































}