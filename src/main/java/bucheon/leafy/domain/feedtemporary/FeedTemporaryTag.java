package bucheon.leafy.domain.feedtemporary;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class FeedTemporaryTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_temporary_tag_id")
    private Long id;

    private String tag;

    @Builder
    public FeedTemporaryTag(String tag) {
        this.tag = tag;
    }

}
