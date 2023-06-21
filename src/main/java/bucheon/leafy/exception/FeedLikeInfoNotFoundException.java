package bucheon.leafy.exception;

public class FeedLikeInfoNotFoundException extends GlobalException {

    private static final String MESSAGE = "좋아요 정보가 없습니다.";

    public FeedLikeInfoNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 500;
    }
}
