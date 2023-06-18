package bucheon.leafy.domain.leafyApi.response;

import bucheon.leafy.domain.search.FlowerGubn;
import lombok.Builder;
import lombok.Data;


@Data
public class LeafyApiResponse {

    private String saleDate;
    private FlowerGubn flowerGubn;
    private String pumName;
    private String goodName;
    private String lv;
    private int maxAmt;
    private int minAmt;
    private int avgAmt;

    @Builder
    private LeafyApiResponse(String saleDate, FlowerGubn flowerGubn, String pumName,
                             String goodName, String lv, int maxAmt, int minAmt, int avgAmt){
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
