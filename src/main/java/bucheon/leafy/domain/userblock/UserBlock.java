package bucheon.leafy.domain.userblock;

import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.util.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBlock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_block_id")
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Feed feed;

    @Builder
    private UserBlock(User user, Feed feed) {
        this.user = user;
        this.feed = feed;
    }

}
