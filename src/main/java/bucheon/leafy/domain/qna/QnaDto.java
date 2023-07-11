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
<<<<<<< HEAD
    private Boolean qnaStatus;
    private String title;
    private Long userId;
    private String mode;

    public QnaDto( String contents, String title, Long userId  ) {
        this.contents = contents;
=======
    private String qnaStatus;
    private String title;
    private Long userId;


    public QnaDto( Boolean isDelete, String contents, String qnaStatus, String title, Long userId) {
        this.isDelete = isDelete;
        this.contents = contents;
        this.qnaStatus = qnaStatus;
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
        this.title = title;
        this.userId = userId;
    }

}