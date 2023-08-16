package bucheon.leafy.domain.qna;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.HashCodeExclude;

import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class QnaDto {
    private Long id;
    private Long userId;
    private String title;
    private String contents;
    private Date modifiedAt;
    private Date createdAt;
    private Long viewCnt;
    private Long commentCnt;

    public QnaDto(String title, String contents, Long userId) {
        this.title = title;
        this.contents = contents;
        this.userId = userId;

    }



}
