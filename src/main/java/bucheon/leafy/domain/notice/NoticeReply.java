package bucheon.leafy.domain.notice;

import bucheon.leafy.domain.board.Board;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.util.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeReply extends BaseDeleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String comment;

    @Builder
    private NoticeReply(User user, Board board, String comment) {
        this.user = user;
        this.board = board;
        this.comment = comment;
    }
}
