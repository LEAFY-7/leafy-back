package bucheon.leafy.domain.result;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Message {
    SUCCESS("요청에 성공했습니다."),
    FAIL("요청에 실패했습니다.");

    private String msg;
}
