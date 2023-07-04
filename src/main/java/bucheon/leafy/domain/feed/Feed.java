package bucheon.leafy.domain.feed;

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
public class Feed extends BaseDeleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long id;

    private String title;

    private String content;

    private Boolean isHide;

    private Boolean isDelete;

    @OneToOne(mappedBy = "feed", cascade = CascadeType.ALL)
    private FeedLikeCount feedLikeCount;

    @JoinColumn(name = "comment_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FeedComment> feedComments = new ArrayList<>();

    @JoinColumn(name = "tag_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FeedTag> feedTags = new ArrayList<>();

    @JoinColumn(name = "feed_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FeedImage> feedImages = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private FeedType feedType;


    @Builder
    private Feed(String title, String content, Boolean isHide,Boolean isDelete,
                 FeedLikeCount feedLikeCount, List<FeedComment> feedComments,
                 List<FeedTag> feedTags, FeedType feedType, List<FeedImage> feedImages) {
        this.title = title;
        this.content = content;
        this.isHide = isHide;
        this.isDelete = isDelete;
        this.feedLikeCount = feedLikeCount;
        this.feedComments = feedComments;
        this.feedTags = feedTags;
        this.feedType = feedType;
        this.feedImages = feedImages;
    }

}
