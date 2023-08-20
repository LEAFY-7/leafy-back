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
    private String contents;
    private String title;
    private Long userId;

    public NoticeDto(String contents, String title, Long userId) {
        this.contents = contents;
        this.title = title;
        this.userId = userId;
    }

    public NoticeDto(Long noticeId, String contents, String title){
        this.noticeId = noticeId;
        this.contents = contents;
        this.title = title;
    }


}
