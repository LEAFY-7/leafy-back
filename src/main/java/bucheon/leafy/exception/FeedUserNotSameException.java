package bucheon.leafy.exception;

public class FeedUserNotSameException extends GlobalException {

    private static final String MESSAGE = "권한이 없습니다.";

    public FeedUserNotSameException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 403;
    }
}
