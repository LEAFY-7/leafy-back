package bucheon.leafy.domain.qna.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QnaEditRequest {
    private String contents;     // 게시물 내용
    private String title;        // 제목
    private Long userId;         // 작성자의 고유 ID

    // 게시물 내용과 제목, 작성자 ID를 받아서 초기화하는 생성자
    public QnaEditRequest(String contents, String title, Long userId) {
        this.contents = contents;
        this.title = title;
        this.userId = userId;
    }
}
