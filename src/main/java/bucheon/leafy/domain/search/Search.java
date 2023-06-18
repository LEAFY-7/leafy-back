package bucheon.leafy.domain.search;

import bucheon.leafy.util.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Search extends BaseDeleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "search_id")
    private Long id;

    private String saleDate;

    @Enumerated(EnumType.STRING)
    private FlowerGubn flowerGubn;

    private String pumName;

    private String goodName;

    private String lv;

    private int maxAmt;

    private int minAmt;

    private int avgAmt;

    private Boolean isDelete;


    @Builder
    private Search(String saleDate, FlowerGubn flowerGubn, String pumName,
                   String goodName, String lv, int maxAmt, int minAmt, int avgAmt,Boolean isDelete) {
        this.saleDate = saleDate;
        this.flowerGubn = flowerGubn;
        this.pumName = pumName;
        this.goodName = goodName;
        this.lv = lv;
        this.maxAmt = maxAmt;
        this.minAmt = minAmt;
        this.avgAmt = avgAmt;
        this.isDelete = isDelete;
    }

}
