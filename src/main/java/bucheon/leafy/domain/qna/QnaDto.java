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
    private String qnaStatus;
    private String title;
    private Long userId;


    public QnaDto( Boolean isDelete, String contents, String qnaStatus, String title, Long userId) {
        this.isDelete = isDelete;
        this.contents = contents;
        this.qnaStatus = qnaStatus;
        this.title = title;
        this.userId = userId;
    }

}