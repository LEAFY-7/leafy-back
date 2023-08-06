package bucheon.leafy.exception;

public class FeedNotFoundException extends GlobalException {

    private static final String MESSAGE = "존재하지 않는 피드입니다.";

    public FeedNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
