package bucheon.leafy.domain.notice;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class NoticeDto {


    private Long id;
    private Date createdAt;
    private Date modifiedAt;
    private boolean isDelete;
    private String contents;
    private boolean isHide;
    private String title;
    private Long userId;

    public NoticeDto( boolean isDelete, String contents, boolean isHide, String title, Long userId) {
        this.isDelete = isDelete;
        this.contents = contents;
        this.isHide = isHide;
        this.title = title;
        this.userId = userId;
    }

}
