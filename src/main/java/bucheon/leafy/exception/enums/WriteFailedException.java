package bucheon.leafy.exception.enums;

import bucheon.leafy.exception.GlobalException;

public class WriteFailedException extends GlobalException {

    private static final String MESSAGE = "글 쓰기가 실패했습니다.";

    public WriteFailedException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }

}
