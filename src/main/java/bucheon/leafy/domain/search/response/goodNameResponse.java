package bucheon.leafy.domain.search.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class goodNameResponse {

    @Schema(description = "품종명")
    private String goodName;


    @Builder
    private goodNameResponse(String goodName){
        this.goodName = goodName;
    }


}
