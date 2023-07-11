package bucheon.leafy.domain.feedLikeInfo;

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
public class FeedLikeInfo extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "feed_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Feed feed;

    @Builder
    private FeedLikeInfo(User user, Feed feed) {
        this.user = user;
        this.feed = feed;
    }

    public static FeedLikeInfo of(User user, Feed feed){
        return FeedLikeInfo.builder()
                .user(user)
                .feed(feed)
                .build();
    }

}

