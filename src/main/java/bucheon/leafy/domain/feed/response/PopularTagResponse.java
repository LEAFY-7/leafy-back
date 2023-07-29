package bucheon.leafy.domain.feed.response;


import lombok.Builder;
import lombok.Data;

@Data
public class PopularTagResponse {

    private String tag;
    private Long count;

    @Builder
    private PopularTagResponse(String tag, Long count) {
        this.tag = tag;
        this.count = count;
    }

    public static PopularTagResponse of(PopularTagInformation popularTagInformation) {
        return PopularTagResponse.builder()
                .tag(popularTagInformation.getTag())
                .count(popularTagInformation.getCount())
                .build();
    }

    public interface PopularTagInformation {
        String getTag();
        Long getCount();
    }

}
