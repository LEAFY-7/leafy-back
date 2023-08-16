package bucheon.leafy.domain.feed;

import bucheon.leafy.util.entity.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feed extends BaseDeleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_id")
    private Long id;

    private String title;

    @Length(max = 50)
    @Pattern(regexp = "^[a-zA-Z가-힣\\s]*$", message = "숫자와 특수문자는 입력할 수 없습니다.")
    private String species;
    @Length(max = 50)
    @Pattern(regexp = "^[a-zA-Z가-힣\\s]*$", message = "숫자와 특수문자는 입력할 수 없습니다.")
    private String nickname;
    private Double temperature;
    private Integer humidity;
    private Double waterAmount;
    private String wateringPeriod;

    private String content;

    @OneToOne(mappedBy = "feed", cascade = CascadeType.ALL)
    private FeedLikeCount feedLikeCount;

    @JoinColumn(name = "feed_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FeedComment> feedComments = new ArrayList<>();

    @JoinColumn(name = "feed_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FeedReply> feedReplies = new ArrayList<>();

    @JoinColumn(name = "feed_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FeedTag> feedTags = new ArrayList<>();

    @JoinColumn(name = "feed_id")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FeedImage> feedImages = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private FeedType feedType;

    @Builder
    public Feed(String title, String species, String nickname, Double temperature,
                Integer humidity, Double waterAmount, String wateringPeriod,
                String content, FeedLikeCount feedLikeCount, List<FeedComment> feedComments,
                List<FeedReply> feedReplies, List<FeedTag> feedTags,
                List<FeedImage> feedImages, FeedType feedType) {

        this.title = title;
        this.species = species;
        this.nickname = nickname;
        this.temperature = temperature;
        this.humidity = humidity;
        this.waterAmount = waterAmount;
        this.wateringPeriod = wateringPeriod;
        this.content = content;
        this.feedLikeCount = feedLikeCount;
        this.feedComments = feedComments;
        this.feedReplies = feedReplies;
        this.feedTags = feedTags;
        this.feedImages = feedImages;
        this.feedType = feedType;
    }

    public void initFeedLikeCount(FeedLikeCount feedLikeCount){
        this.feedLikeCount = feedLikeCount;
    }

    public void addFeedImages(List<String> imageNames, List<Integer> imageHeights){
        List<FeedImage> feedImages = IntStream.range(0, Math.min(imageNames.size(), imageHeights.size()))
                .mapToObj(i -> FeedImage.of(imageNames.get(i), imageHeights.get(i)))
                .collect(Collectors.toList());

        this.feedImages = feedImages;
    }
}
