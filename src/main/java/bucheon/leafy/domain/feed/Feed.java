package bucheon.leafy.domain.feed;

import bucheon.leafy.util.BaseDeleteEntity;
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
    private FeedLike feedLike;

    @JoinColumn(name = "feed_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FeedComment> feedComment = new ArrayList<>();

    @JoinColumn(name = "feed_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FeedTag> feedTag = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private FeedType feedType;


    @Builder
    private Feed(String title, String content, Boolean isHide,Boolean isDelete, FeedLike feedLike,
                  List<FeedComment> feedComment, List<FeedTag> feedTag, FeedType feedType) {
        this.title = title;
        this.content = content;
        this.isHide = isHide;
        this.isDelete = isDelete;
        this.feedLike = feedLike;
        this.feedComment = feedComment;
        this.feedTag = feedTag;
        this.feedType = feedType;
    }

}
