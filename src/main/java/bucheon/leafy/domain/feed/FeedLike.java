package bucheon.leafy.domain.feed;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class FeedLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "feed_id")
    private Feed feed;

    private Long likeCount;

    @Builder
    private FeedLike(Long likeCount) {
        this.likeCount = likeCount;
    }
}
