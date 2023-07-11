package bucheon.leafy.domain.notice;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class NoticeDto {

<<<<<<< HEAD
=======

>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
    private Long id;
    private Date createdAt;
    private Date modifiedAt;
    private boolean isDelete;
    private String contents;
    private boolean isHide;
    private String title;
    private Long userId;

<<<<<<< HEAD
    public NoticeDto(Long id, boolean isDelete, String contents, boolean isHide, String title, Long userId) {
        this.id = id;
=======
    public NoticeDto( boolean isDelete, String contents, boolean isHide, String title, Long userId) {
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
        this.isDelete = isDelete;
        this.contents = contents;
        this.isHide = isHide;
        this.title = title;
        this.userId = userId;
    }

}
