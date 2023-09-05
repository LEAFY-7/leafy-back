package bucheon.leafy.domain.qna;

import bucheon.leafy.domain.user.User;
import bucheon.leafy.util.entity.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@SQLDelete(sql = "update qnaComment set is_delete = true where qna_comment_id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QnaComment extends BaseDeleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qna_comment_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String comment;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "qna_comment_id")
    private List<QnaReply> qnaReplies = new ArrayList<>();

    @Builder
    private QnaComment(User user, String comment, List<QnaReply> qnaReplies) {
        this.user = user;
        this.comment = comment;
        this.qnaReplies = qnaReplies;
    }
}

