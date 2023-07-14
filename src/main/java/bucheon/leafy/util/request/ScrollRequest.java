package bucheon.leafy.util.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScrollRequest {

    // 마지막 페이지일 경우, 키값이 1일 수 없기 때문에
    public static final Long NONE_KEY = 1L;
    public static final int size = 20;
    private Long key;

    public ScrollRequest(Long key) {
        this.key = key;
    }

    // 넘어온 마지막 피드id가 null이 아니고 1L(마지막 피드)이 아닐때 = 다음 페이지를 요청할때 <> id가 null이거나(첫번째 페이지) 마지막 피드일때?
    // 첫번째 페이지 = id가 null임
    // 마지막 페이지 = id가 null이 아니고 1임
    public Boolean hasKey() {
        return key != null && !key.equals(NONE_KEY);
    }

    public ScrollRequest next(Long key) {
        return new ScrollRequest(key);
    }

}
