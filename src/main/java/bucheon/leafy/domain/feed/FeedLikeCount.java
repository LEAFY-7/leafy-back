package bucheon.leafy.domain.feed;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(indexes = @Index(columnList = "feed_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedLikeCount {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "feed_id")
    private Feed feed;

    private Long likeCount;

    @Builder
    private FeedLikeCount(Long likeCount, Feed feed) {
        this.likeCount = likeCount;
        this.feed = feed;
    }

    public static FeedLikeCount of(Long likeCount, Feed feed) {
        return FeedLikeCount.builder()
                .likeCount(likeCount)
                .feed(feed)
                .build();
    }

    public void initFeed(Feed feed) {
        this.feed = feed;
    }

    public void like() {
        this.likeCount += 1;
    }

    public void likeCancel() {
        this.likeCount -= 1;
    }
}
