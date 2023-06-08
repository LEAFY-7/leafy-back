package bucheon.leafy.domain.qna;

import bucheon.leafy.domain.user.User;
import bucheon.leafy.util.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Qna extends BaseDeleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    private QnaStatus qnaStatus;

    private Qna(String title, String contents, User user, QnaStatus qnaStatus) {
        this.title = title;
        this.contents = contents;
        this.user = user;
        this.qnaStatus = qnaStatus;
    }

}
