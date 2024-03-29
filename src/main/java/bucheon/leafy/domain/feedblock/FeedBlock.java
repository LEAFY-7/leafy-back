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
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "feed_id"})
        }
)
public class FeedBlock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_block_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @Builder
    private FeedBlock(User user, Feed feed) {
        this.user = user;
        this.feed = feed;
    }

    public static FeedBlock of(User user, Feed feed) {
        return FeedBlock.builder()
                .user(user)
                .feed(feed)
                .build();
    }

}
