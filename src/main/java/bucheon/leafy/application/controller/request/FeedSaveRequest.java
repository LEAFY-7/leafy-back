package bucheon.leafy.application.controller.request;

import bucheon.leafy.domain.feed.FeedType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class FeedSaveRequest {

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

    private List<String> tagList;

    private List<MultipartFile> fileList;


    @Builder
    public FeedSaveRequest(String title, String species, String nickname,
                           Double temperature, Integer humidity, Double waterAmount,
                           String wateringPeriod, String content, FeedType feedType,
                           List<String> tagList, List<MultipartFile> fileList) {

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
        this.fileList = fileList;
    }
}
