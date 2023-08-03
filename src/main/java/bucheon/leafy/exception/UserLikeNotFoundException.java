package bucheon.leafy.exception;

public class UserLikeNotFoundException extends GlobalException {

    private static final String MESSAGE = "좋아요를 누른적 없습니다.";

    public UserLikeNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 500;
    }
}
