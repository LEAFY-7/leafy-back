package bucheon.leafy.domain.qna;

import bucheon.leafy.util.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Qna extends BaseDeleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    private Long viewCount;

    @JoinColumn(name = "qna_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QnaComment> qnaComments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private QnaStatus qnaStatus;

<<<<<<< HEAD
    private Qna(String title, String contents, QnaStatus qnaStatus, Long viewCount) {
=======
    private Qna(String title, String contents, QnaStatus qnaStatus ) {
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
        this.title = title;
        this.contents = contents;
        this.qnaStatus = qnaStatus;
        this.viewCount = viewCount;
    }

}
