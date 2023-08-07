package bucheon.leafy.domain.feedtemporary;

import bucheon.leafy.util.entity.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedTemporary extends BaseDeleteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_temporary_id")
    private Long id;

    private String title;

    private String content;

    @JoinColumn(name = "feed_temporary_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FeedTemporaryTag> feedTemporaryTags;

    @JoinColumn(name = "feed_temporary_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FeedTemporaryImage> feedTemporaryImages;


    @Builder
    private FeedTemporary(String title, String content,
                          List<FeedTemporaryTag> feedTemporaryTags,
                          List<FeedTemporaryImage> feedTemporaryImages) {

        this.title = title;
        this.content = content;
        this.feedTemporaryTags = feedTemporaryTags;
        this.feedTemporaryImages = feedTemporaryImages;

    }

}
