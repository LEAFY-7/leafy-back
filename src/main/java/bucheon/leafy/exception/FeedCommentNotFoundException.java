package bucheon.leafy.exception;

public class FeedCommentNotFoundException extends GlobalException {
    private static final String MESSAGE = "존재하지 않는 댓글입니다.";

    public FeedCommentNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 500;
    }
}
