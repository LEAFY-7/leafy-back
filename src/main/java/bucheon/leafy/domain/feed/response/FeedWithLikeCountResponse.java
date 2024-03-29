package bucheon.leafy.domain.feed.response;

import bucheon.leafy.domain.feed.Feed;
import lombok.Builder;
import lombok.Data;

import static bucheon.leafy.path.S3Path.*;


@Data
public class FeedWithLikeCountResponse {

    private Long feedId;

    private String title;

    private String content;

    private Long likeCount;

    private String image;

    @Builder
    private FeedWithLikeCountResponse(Long feedId, String title,
                                     String content, Long likeCount, String image) {
        this.feedId = feedId;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.image = image;
    }

    public static FeedWithLikeCountResponse of(Feed feed) {
        return FeedWithLikeCountResponse.builder()
                .feedId(feed.getId())
                .title(feed.getTitle())
                .content(feed.getContent())
                .likeCount(feed.getFeedLikeCount().getLikeCount())
                .image(
                        feed.getFeedImages().isEmpty() ?
                                ABSOLUTE_PATH + FEED_PATH + DEFAULT_IMAGE
                                : ABSOLUTE_PATH + FEED_PATH + feed.getFeedImages().get(0).getImageName()
                )
                .build();
    }

}
