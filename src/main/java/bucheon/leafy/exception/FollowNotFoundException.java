package bucheon.leafy.exception;

public class FollowNotFoundException extends GlobalException {

    private static final String MESSAGE = "팔로우 상태가 아닙니다.";

    public FollowNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
