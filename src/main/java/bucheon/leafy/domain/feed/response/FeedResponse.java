package bucheon.leafy.domain.feed.response;

import bucheon.leafy.domain.feed.FeedType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FeedResponse {

    private Long feedId;
    private Long userId;
    private String userName;
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

    // JPA 에서 DTO 타입으로 값을 받기 위해 public 으로 생성자를 열어 놓음
    @Builder
    public FeedResponse(Long feedId, Long userId, String userName, String title,
                        String content, String species, String nickname, Double temperature,
                        Integer humidity, Double waterAmount, String wateringPeriod,
                        FeedType feedType, LocalDateTime createdAt, LocalDateTime modifiedAt) {

        this.feedId = feedId;
        this.userId = userId;
        this.userName = userName;
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
