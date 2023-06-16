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
public class QnaComment extends BaseDeleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String comment;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_comment_id")
    private List<QnaReply> noticeReplies = new ArrayList<>();

    @Builder
    private QnaComment(User user, String comment, List<QnaReply> noticeReplies) {
        this.user = user;
        this.comment = comment;
        this.noticeReplies = noticeReplies;
    }
}

