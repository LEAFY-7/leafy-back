package bucheon.leafy.exception;

public class UserNotVerifiedException extends GlobalException {

    private static final String MESSAGE = "유저의 정보가 검증되지 않습니다.";

    public UserNotVerifiedException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }

}
