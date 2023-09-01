package bucheon.leafy.domain.qna.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QnaEditRequest {
    private String contents;     // 게시물 내용
    private String title;        // 제목
    public QnaEditRequest(String contents, String title) {
        this.contents = contents;
        this.title = title;
    }
}
