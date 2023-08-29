package bucheon.leafy.domain.notice.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeEditRequest {
    private String contents;
    private String title;
    private Boolean isHide;

    public NoticeEditRequest( String contents, String title, Boolean isHide){
        this.contents = contents;
        this.title = title;
        this.isHide = isHide;
    }


}
