package bucheon.leafy.domain.qna;

import bucheon.leafy.domain.user.User;
import bucheon.leafy.util.entity.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Getter
@Entity
@SQLDelete(sql = "update qnaReply set is_delete = true where qna_reply_id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QnaReply extends BaseDeleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qna_reply_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String comment;

    @Builder
    private QnaReply(User user, String comment) {
        this.user = user;
        this.comment = comment;
    }
}
