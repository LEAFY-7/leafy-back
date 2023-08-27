package bucheon.leafy.domain.feed.response;

import bucheon.leafy.domain.feed.FeedType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class FeedResponse {

    private Long feedId;
    private Long userId;
    private String userNickName;
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
    private List<FeedImageResponse> feedImages = new ArrayList<>();

    @Builder
    public FeedResponse(Long feedId, Long userId, String userNickName, String title,
                            String content, String species, String nickname, Double temperature,
                            Integer humidity, Double waterAmount, String wateringPeriod,
                            FeedType feedType, LocalDateTime createdAt, LocalDateTime modifiedAt) {

        this.feedId = feedId;
        this.userId = userId;
        this.userNickName = userNickName;
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
    }

}
