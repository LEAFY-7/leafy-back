package bucheon.leafy.domain.qna;

import lombok.*;

import java.time.LocalTime;
import java.util.*;

@Data
public class QnaDto {
    private Integer id;
    private Date createdAt;
    private Date modifiedAt;
    private Integer is_delete;
    private String contents;
    private String qna_status;
    private String title;
    private Integer user_id;

    public QnaDto() {  }
    public QnaDto(Integer id,String title, String contents, Integer user_id) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.user_id = user_id;
    }

}
