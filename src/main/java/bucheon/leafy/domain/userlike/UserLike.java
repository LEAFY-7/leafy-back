package bucheon.leafy.domain.userlike;

import bucheon.leafy.domain.board.Board;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.util.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLike extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @Builder
    private UserLike(User user, Board board) {
        this.user = user;
        this.board = board;
    }
}
