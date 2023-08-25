package bucheon.leafy.domain.notice.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeEditRequest {
    private String contents;
    private String title;

    public NoticeEditRequest( String contents, String title){
        this.contents = contents;
        this.title = title;
    }


}
