package bucheon.leafy.domain.qna;

import bucheon.leafy.domain.user.User;
import bucheon.leafy.util.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Qna extends BaseDeleteEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    private Boolean isHide;

    @Enumerated(EnumType.STRING)
    private QnaType qnaType;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private List<QnaComment> qnaComments = new ArrayList<>();

    @Builder
    private Qna(String title, String contents, QnaType qnaType,
                User user, List<QnaComment> qnaComments, Boolean isHide) {

        this.title = title;
        this.contents = contents;
        this.qnaType = qnaType;
        this.user = user;
        this.qnaComments = qnaComments;
        this.isHide = isHide;
    }
}