package bucheon.leafy.domain.userlike;

import bucheon.leafy.domain.feed.Feed;
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
    private Feed feed;

    @Builder
    private UserLike(User user, Feed feed) {
        this.user = user;
        this.feed = feed;
    }
}
