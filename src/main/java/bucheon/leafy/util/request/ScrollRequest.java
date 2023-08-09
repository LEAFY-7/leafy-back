package bucheon.leafy.util.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScrollRequest {

    // 마지막 페이지일 경우, 키값이 -1일 수 없기 때문에
    public static final Long NONE_KEY = -1L;
    public static final int size = 20;
    private Long key;

    public ScrollRequest(Long key) {
        this.key = key;
    }

    public Boolean hasKey() {
        return key != null && !key.equals(NONE_KEY) && key != 0;
    }

    public ScrollRequest next(Long key) {
        return new ScrollRequest(key);
    }

}
