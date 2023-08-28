package bucheon.leafy.domain.search.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchResponse {

    private Long searchId;
    @Schema(description = "경매일자")
    private String saleDate;
    @Schema(description = "화훼부류명")
    private String flowerGubn;
    @Schema(description = "품목명")
    private String pumName;
    @Schema(description = "품종명")
    private String goodName;
    @Schema(description = "등급명")
    private String lv;
    @Schema(description = "최고가")
    private int maxAmt;
    @Schema(description = "최저가")
    private int minAmt;
    @Schema(description = "평균가")
    private int avgAmt;


    @Builder
    private SearchResponse(Long searchId, String saleDate, String flowerGubn, String pumName,
                           String goodName, String lv, int maxAmt, int minAmt, int avgAmt){
        this.searchId = searchId;
        this.saleDate = saleDate;
        this.flowerGubn = flowerGubn;
        this.pumName = pumName;
        this.goodName = goodName;
        this.lv = lv;
        this.maxAmt = maxAmt;
        this.minAmt = minAmt;
        this.avgAmt = avgAmt;
    }


}
