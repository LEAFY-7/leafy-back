package bucheon.leafy.domain.feed.response;


import lombok.Builder;
import lombok.Data;

@Data
public class FeedMonthlyResponse {

    private Integer year;
    private Integer month;
    private Long count;

    @Builder
    private FeedMonthlyResponse(Integer year, Integer month, Long count) {
        this.year = year;
        this.month = month;
        this.count = count;
    }
    
    public static FeedMonthlyResponse of(FeedMonthlyInformation feedMonthlyInformation){
        return FeedMonthlyResponse.builder()
                .year(feedMonthlyInformation.getYear())
                .month(feedMonthlyInformation.getMonth())
                .count(feedMonthlyInformation.getCount())
                .build();
    }
    
}
