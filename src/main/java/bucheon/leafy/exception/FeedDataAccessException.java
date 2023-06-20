package bucheon.leafy.exception;

public class FeedDataAccessException extends GlobalException{

    private static final String MESSAGE = "피드 수정이 실패했습니다.";

    public FeedDataAccessException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 500;
    }
}
