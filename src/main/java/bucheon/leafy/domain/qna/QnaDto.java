package bucheon.leafy.domain.qna;

import lombok.*;


import java.util.*;
@NoArgsConstructor
@Data
public class QnaDto {
    private Long id;
    private Date createdAt;
    private Date modifiedAt;
    private Boolean isDelete;
    private String contents;
    private Boolean qnaStatus;
    private String title;
    private Long userId;
    private String mode;

    public QnaDto( String contents, String title, Long userId  ) {
        this.contents = contents;
        this.title = title;
        this.userId = userId;
    }

}