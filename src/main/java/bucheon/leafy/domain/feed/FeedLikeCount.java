package bucheon.leafy.domain.feed;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedLikeCount {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "feed_id")
    private Feed feed;

    private Long likeCount;

    @Builder
    private FeedLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public static FeedLikeCount of(Long likeCount) {
        return FeedLikeCount.builder()
                .likeCount(likeCount)
                .build();
    }

    public void like() {
        this.likeCount = this.likeCount + 1;
    }

    public void likeCancel() {
        this.likeCount = this.likeCount - 1;
    }

    public void initFeed(Feed feed) {
        this.feed = feed;
    }

}
