package bucheon.leafy.domain.notice;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class NoticeDto {
    private Long noticeId;
    private Date createdAt;
    private Date modifiedAt;
    private Boolean isDelete;
    private String contents;
    private Boolean isHide;
    private String title;
    private Boolean viewCount;
    private Long userId;

    public NoticeDto(String contents, String title, Long userId) {
        this.contents = contents;
        this.title = title;
        this.userId = userId;
    }


}
