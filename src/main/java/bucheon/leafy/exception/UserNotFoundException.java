package bucheon.leafy.exception;

public class UserNotFoundException extends GlobalException {

    private static final String MESSAGE = "존재하지 않는 회원입니다.";

    public UserNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 500;
    }

}
