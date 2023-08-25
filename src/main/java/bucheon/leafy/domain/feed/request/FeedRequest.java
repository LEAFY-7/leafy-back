package bucheon.leafy.domain.feed.request;

import bucheon.leafy.domain.feed.FeedType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class FeedRequest {

    @JsonIgnore
    private Long feedId;

    private String title;

    @Schema(description = "학명")
    private String species;

    @Schema(description = "별명")
    private String nickname;

    @Schema(description = "온도")
    private Double temperature;

    @Schema(description = "습도")
    private Integer humidity;

    @Schema(description = "물양")
    private Double waterAmount;

    @Schema(description = "물주는기간")
    private String wateringPeriod;

    @Schema(description = "상세입력")
    private String content;

    private FeedType feedType;

    private List<FeedTagRequest> tagList;

    private List<MultipartFile> imageList;

    @Builder
    public FeedRequest(String title, String species, String nickname,
                           Double temperature, Integer humidity, Double waterAmount,
                           String wateringPeriod, String content, FeedType feedType,
                           List<FeedTagRequest> tagList, List<MultipartFile> imageList) {

        this.title = title;
        this.species = species;
        this.nickname = nickname;
        this.temperature = temperature;
        this.humidity = humidity;
        this.waterAmount = waterAmount;
        this.wateringPeriod = wateringPeriod;
        this.content = content;
        this.feedType = feedType;
        this.tagList = tagList;
        this.imageList = imageList;
    }
}
