package bucheon.leafy.domain.feed.response;

import bucheon.leafy.domain.feed.FeedType;
import bucheon.leafy.domain.user.response.FeedAuthorResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class FeedResponse {

    private Long feedId;
    private String title;
    private String content;
    private String species;
    private String nickname;
    private Double temperature;
    private Integer humidity;
    private Double waterAmount;
    private String wateringPeriod;
    private FeedType feedType;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long likeCount;
    private FeedAuthorResponse feedAuthorResponse;
    private List<FeedImageResponse> feedImages = new ArrayList<>();

    @Builder
    public FeedResponse(Long feedId, String title, String content,
                        String species, String nickname, Double temperature,
                        Integer humidity, Double waterAmount, String wateringPeriod,
                        FeedType feedType, LocalDateTime createdAt, LocalDateTime modifiedAt,
                        Long userId, String userNickName, String profileImage,
                        Long likeCount) {

        this.feedId = feedId;
        this.title = title;
        this.content = content;
        this.species = species;
        this.nickname = nickname;
        this.temperature = temperature;
        this.humidity = humidity;
        this.waterAmount = waterAmount;
        this.wateringPeriod = wateringPeriod;
        this.feedType = feedType;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.likeCount = likeCount;

        this.feedAuthorResponse = FeedAuthorResponse.builder()
                .userId(userId)
                .userNickName(userNickName)
                .profileImage(profileImage)
                .build();
    }

}
