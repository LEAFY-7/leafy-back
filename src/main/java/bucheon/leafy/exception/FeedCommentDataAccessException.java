package bucheon.leafy.exception;

public class FeedCommentDataAccessException extends GlobalException{

    private static final String MESSAGE = "댓글 수정에 실패했습니다.";

    public FeedCommentDataAccessException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 500;
    }
}
