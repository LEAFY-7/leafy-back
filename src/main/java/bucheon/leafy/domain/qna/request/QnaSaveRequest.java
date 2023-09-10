package bucheon.leafy.domain.qna.request;
import bucheon.leafy.domain.qna.QnaType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import static bucheon.leafy.domain.qna.QnaType.HOLD;

@Data
@NoArgsConstructor
public class QnaSaveRequest {

    @JsonIgnore
    private Long qnaId;

    @JsonIgnore
    private QnaType qnaStatus = HOLD;

    private String contents;     // 게시물 내용
    private String title;        // 제목




    // 게시물 내용과 제목, 작성자 ID를 받아서 초기화하는 생성자
    public QnaSaveRequest(Long qnaId, String contents, String title ) {
        this.qnaId = qnaId;
        this.contents = contents;
        this.title = title;

    }
}
