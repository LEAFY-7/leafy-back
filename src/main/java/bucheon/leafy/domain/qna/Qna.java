package bucheon.leafy.domain.qna;

import bucheon.leafy.util.entity.BaseDeleteEntity;
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

    @JoinColumn(name = "qna_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QnaComment> qnaComments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private QnaStatus qnaStatus;

    private Qna(String title, String contents, QnaStatus qnaStatus) {
        this.title = title;
        this.contents = contents;
        this.qnaStatus = qnaStatus;
    }

}
