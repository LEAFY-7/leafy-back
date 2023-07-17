package bucheon.leafy.domain.leafyapi;

import lombok.*;


@Getter
@Setter
public class LeafyApiDto {

    private String saleDate;
    private String flowerGubn;
    private String pumName;
    private String goodName;
    private String lv;
    private int maxAmt;
    private int minAmt;
    private int avgAmt;

    @Builder
    public LeafyApiDto(String saleDate, String flowerGubn, String pumName,
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
