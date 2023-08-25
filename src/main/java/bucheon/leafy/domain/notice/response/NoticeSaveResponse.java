package bucheon.leafy.domain.notice.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeSaveResponse {
    private String contents;
    private String title;
    private Long userId;

    public NoticeSaveResponse(String contents, String title, Long userId) {
        this.contents = contents;
        this.title = title;
        this.userId = userId;
    }







































































}
