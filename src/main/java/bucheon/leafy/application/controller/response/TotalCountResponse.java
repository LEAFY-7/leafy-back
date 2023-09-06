package bucheon.leafy.application.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
public class TotalCountResponse {

    private Long feedCount;

    private Long likeCount;

    private Long followerCount;

    private Long followingCount;


    @Builder
    private TotalCountResponse(Long feedCount, Long likeCount, Long followerCount, Long followingCount) {

        this.feedCount = feedCount;
        this.likeCount = likeCount;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
    }

    public static TotalCountResponse of(Long feedCount, Long likeCount,
                                        Long followerCount, Long followingCount) {

        return TotalCountResponse.builder()
                .followerCount(feedCount)
                .likeCount(likeCount)
                .followerCount(followerCount)
                .followingCount(followingCount)
                .build();

    }

}
