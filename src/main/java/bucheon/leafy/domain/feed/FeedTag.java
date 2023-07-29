package bucheon.leafy.domain.feed;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class FeedTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_tag_id")
    private Long id;

    private String tag;

    @Builder
    private FeedTag(String tag) {
        this.tag = tag;
    }

    public static FeedTag of(String tag){
        return FeedTag.builder()
                .tag(tag)
                .build();
    }

}
