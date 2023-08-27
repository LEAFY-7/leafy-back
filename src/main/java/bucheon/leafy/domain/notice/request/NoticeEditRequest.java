package bucheon.leafy.domain.notice.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
