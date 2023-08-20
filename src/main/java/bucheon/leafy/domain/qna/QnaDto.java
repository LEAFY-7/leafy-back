package bucheon.leafy.domain.qna;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.HashCodeExclude;

import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class QnaDto {
    private Long qnaId;          // Q&A 게시물의 고유 ID
    private Date createdAt;      // 작성일
    private Date modifiedAt;     // 수정일
    private Boolean isDelete;    // 삭제 여부
    private String contents;     // 게시물 내용
    private String qnaStatus;    // 게시물 상태
    private String title;        // 제목
    private Long viewCount;      // 조회수
    private Long userId;         // 작성자의 고유 ID

    // qna_comment에 대한 추가 속성
    private Long qnaCommentId;   // 댓글의 고유 ID
    private String comment;     // 댓글 내용

    // qna_reply에 대한 추가 속성
    private Long qnaReplyCount;


    // 게시물 내용과 제목, 작성자 ID를 받아서 초기화하는 생성자
    public QnaDto(String contents, String title, Long userId) {
        this.contents = contents;
        this.title = title;
        this.userId = userId;
    }
}
