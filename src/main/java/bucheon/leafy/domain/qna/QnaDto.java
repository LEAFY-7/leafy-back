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
    private Long qnaId;
    private Date createdAt;
    private Date modifiedAt;
    private Boolean isDelete;
    private String contents;
    private String qnaStatus;
    private String title;
    private Long viewCount;
    private Long userId;

    public QnaDto(String contents, String title, Long userId) {
        this.contents = contents;
        this.title = title;
        this.userId = userId;

    }



}
