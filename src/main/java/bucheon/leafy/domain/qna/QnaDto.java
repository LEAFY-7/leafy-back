package bucheon.leafy.domain.qna;

import lombok.*;
<<<<<<< HEAD
import java.util.*;

=======


import java.util.*;
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
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