package bucheon.leafy.exception.enums;

import bucheon.leafy.exception.GlobalException;

public class ReadFailedException extends GlobalException {

    private static final String MESSAGE = "읽기이 실패했습니다.";

    public ReadFailedException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }

}
