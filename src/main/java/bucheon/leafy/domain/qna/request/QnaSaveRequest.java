package bucheon.leafy.domain.qna.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QnaSaveRequest {
    private String contents;     // 게시물 내용
    private String title;        // 제목



    // 게시물 내용과 제목, 작성자 ID를 받아서 초기화하는 생성자
    public QnaSaveRequest(String contents, String title, String userId) {
        this.contents = contents;
        this.title = title;

    }
}