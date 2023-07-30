package bucheon.leafy.domain.feedblock;

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
public class FeedBlock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_block_id")
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Feed feed;

    @Builder
    private FeedBlock(User user, Feed feed) {
        this.user = user;
        this.feed = feed;
    }

}
