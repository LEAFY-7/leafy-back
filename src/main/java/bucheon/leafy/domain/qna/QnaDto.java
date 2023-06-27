package bucheon.leafy.domain.qna;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
public class QnaDto {
    private Integer id;
    private String userId;
    private String title;
    private String contents;
    private Date modifiedAt;
    private Date createdAt;
    private Integer viewCnt;
    private Integer commentCnt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QnaDto qnaDto = (QnaDto) o;
        return viewCnt == qnaDto.viewCnt && commentCnt == qnaDto.commentCnt && Objects.equals(id, qnaDto.id) && Objects.equals(userId, qnaDto.userId) && Objects.equals(title, qnaDto.title) && Objects.equals(contents, qnaDto.contents) && Objects.equals(id, qnaDto.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, title, contents, userId);
    }
    public QnaDto() { this("","",""); }
    public QnaDto(String title, String contents, String userId) {
        this.title = title;
        this.contents = contents;
        this.userId = userId;

    }



}
