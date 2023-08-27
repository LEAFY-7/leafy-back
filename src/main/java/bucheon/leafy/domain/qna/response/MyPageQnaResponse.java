package bucheon.leafy.domain.qna.response;

import bucheon.leafy.domain.qna.Qna;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MyPageQnaResponse {
    private Long qnaId;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private String title;
    private String contents;

    @Builder
    private MyPageQnaResponse(Long qnaId, LocalDateTime createAt, LocalDateTime modifiedAt,
                             String contents, String title) {
        this.qnaId = qnaId;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
        this.contents = contents;
        this.title = title;
    }

    public static MyPageQnaResponse of(Qna qna) {
        return MyPageQnaResponse.builder()
                .qnaId(qna.getId())
                .createAt(qna.getCreatedAt())
                .modifiedAt(qna.getModifiedAt())
                .contents(qna.getContents())
                .title(qna.getTitle())
                .build();
    }

}
