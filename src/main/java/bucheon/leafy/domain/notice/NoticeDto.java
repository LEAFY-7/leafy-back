package bucheon.leafy.domain.notice;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class NoticeDto {

    private long id;
    private Date createdAt;
    private Date modifiedAt;
    private boolean isDelete;
    private String contents;
    private boolean isHide;
    private String title;
    private long userId;
    public NoticeDto( String contents, boolean isHide, String title, long userId ) {
        this.contents = contents;
        this.isHide = isHide;
        this.title = title;
        this.userId = userId;
    }
    public NoticeDto(long id, String contents, boolean isHide, String title) {
        this.id = id;
        this.contents = contents;
        this.isHide = isHide;
        this.title = title;
        this.modifiedAt = modifiedAt;
    }

}
