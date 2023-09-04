package bucheon.leafy.exception;

public class QnaCommentNotFoundException extends GlobalException {
    private static final String MESSAGE = "존재하지 않는 QnaComment입니다.";

    public QnaCommentNotFoundException() {super(MESSAGE);}

    @Override
    public int getStatusCode() {
        return 404;
    }

}
