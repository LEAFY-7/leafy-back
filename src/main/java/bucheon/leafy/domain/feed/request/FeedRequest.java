package bucheon.leafy.domain.feed.request;

import bucheon.leafy.domain.feed.FeedType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class FeedRequest {

    @JsonIgnore
    private Long feedId;

    @NotNull
    private String title;

    @NotNull
    @Schema(description = "학명")
    private String species;

    @NotNull
    @Schema(description = "별명")
    private String nickname;

    @NotNull
    @Schema(description = "온도")
    private Double temperature;

    @NotNull
    @Schema(description = "습도")
    private Integer humidity;

    @NotNull
    @Schema(description = "물양")
    private Double waterAmount;

    @NotNull
    @Schema(description = "물주는기간")
    private String wateringPeriod;

    @NotNull
    @Schema(description = "상세입력")
    private String content;

    @NotNull
    private FeedType feedType;

    private List<String> tagList;

    private List<MultipartFile> imageList;

    @Builder
    public FeedRequest(String title, String species, String nickname,
                           Double temperature, Integer humidity, Double waterAmount,
                           String wateringPeriod, String content, FeedType feedType,
                           List<String> tagList, List<MultipartFile> imageList) {

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
