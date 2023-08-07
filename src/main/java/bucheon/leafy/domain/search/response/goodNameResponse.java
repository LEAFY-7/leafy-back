package bucheon.leafy.domain.search.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class goodNameResponse {
    private String goodName;


    @Builder
    private goodNameResponse(String goodName){
        this.goodName = goodName;
    }


}
