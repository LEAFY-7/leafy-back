package bucheon.leafy.domain.notice;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class NoticeDto {

<<<<<<< HEAD
    private long id;
=======
    private Long id;
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
    private Date createdAt;
    private Date modifiedAt;
    private boolean isDelete;
    private String contents;
    private boolean isHide;
    private String title;
<<<<<<< HEAD
    private long userId;
    public NoticeDto( String contents, boolean isHide, String title, long userId ) {
=======
    private Long userId;

    public NoticeDto(Long id, boolean isDelete, String contents, boolean isHide, String title, Long userId) {
        this.id = id;
        this.isDelete = isDelete;
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
        this.contents = contents;
        this.isHide = isHide;
        this.title = title;
        this.userId = userId;
    }
<<<<<<< HEAD
    public NoticeDto(long id, String contents, boolean isHide, String title) {
        this.id = id;
        this.contents = contents;
        this.isHide = isHide;
        this.title = title;
        this.modifiedAt = modifiedAt;
    }
=======
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde

}
