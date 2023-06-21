package bucheon.leafy.domain.feed;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedLikeCount {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "feed_id")
    private Feed feed;

    // 원자적(Atomic)으로 처리되어 다른 스레드와 동시에 값이 변경되더라도 일관된 결과를 얻는다!
    private AtomicLong likeCount;

    @Builder
    private FeedLikeCount(AtomicLong likeCount) {
        this.feed = feed;
        this.likeCount = likeCount;
    }

    public static FeedLikeCount of(AtomicLong likeCount) {
        return FeedLikeCount.builder()
                .likeCount(likeCount)
                .build();
    }

    public void like() {
        this.likeCount.incrementAndGet();
    }

    public void likeCancel() {
        this.likeCount.decrementAndGet();
    }

}
