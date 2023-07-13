package bucheon.leafy.domain.search;

import bucheon.leafy.util.entity.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Search extends BaseDeleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "search_id")
    private Long id;

    @NotNull
    private String saleDate;
    @NotNull
    private String flowerGubn;
    @NotNull
    private String pumName;
    @NotNull
    private String goodName;
    @NotNull
    private String lv;
    @NotNull
    private int maxAmt;
    @NotNull
    private int minAmt;
    @NotNull
    private int avgAmt;


    @Builder
    private Search(String saleDate, String flowerGubn, String pumName,
                   String goodName, String lv, int maxAmt, int minAmt) {
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
