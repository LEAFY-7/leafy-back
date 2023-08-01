package bucheon.leafy.domain.feed.request;

import bucheon.leafy.domain.feed.FeedType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class FeedRequest {

    @JsonIgnore
    private Long feedId;
    private String title;
    private String species;
    private String nickname;
    private Double temperature;
    private Integer humidity;
    private Double waterAmount;
    private String wateringPeriod;
    private String content;
    private FeedType feedType;

    @Builder
    public FeedRequest(String title, String species, String nickname, Double temperature, Integer humidity, Double waterAmount, String wateringPeriod, String content, FeedType feedType) {
        this.title = title;
        this.species = species;
        this.nickname = nickname;
        this.temperature = temperature;
        this.humidity = humidity;
        this.waterAmount = waterAmount;
        this.wateringPeriod = wateringPeriod;
        this.content = content;
        this.feedType = feedType;
    }
}
