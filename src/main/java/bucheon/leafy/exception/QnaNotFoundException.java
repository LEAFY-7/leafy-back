package bucheon.leafy.exception;

public class QnaNotFoundException extends GlobalException {
    private static final String MESSAGE = "존재하지 않는 Qna입니다.";

    public QnaNotFoundException() {super(MESSAGE);}

    @Override
    public int getStatusCode() {
        return 404;
    }

}
