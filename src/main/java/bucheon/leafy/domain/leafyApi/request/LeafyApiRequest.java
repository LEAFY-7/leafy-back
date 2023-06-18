package bucheon.leafy.domain.leafyApi.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class LeafyApiRequest {

    @Schema(description = "화훼구분코드")
    @NotBlank(message = "화훼구분코드가 누락되었습니다.")
    @Size(min=1, max=4)
    private int flowerGubn;
}
