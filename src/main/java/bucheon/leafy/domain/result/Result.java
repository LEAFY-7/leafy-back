package bucheon.leafy.domain.result;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Result {
    private int code;
    private String msg;
    private Object response;


    @Builder
    public Result(int code, String msg, Object response) {
        this.code = code;
        this.msg = msg;
        this.response = response;
    }

}
