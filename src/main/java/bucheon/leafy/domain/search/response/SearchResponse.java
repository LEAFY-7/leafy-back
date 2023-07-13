package bucheon.leafy.domain.search.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchResponse {

    private Long searchId;
    private String saleDate;
    private String flowerGubn;
    private String pumName;
    private String goodName;
    private String lv;
    private int maxAmt;
    private int minAmt;
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
