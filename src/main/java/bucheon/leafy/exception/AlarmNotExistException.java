package bucheon.leafy.exception;

public class AlarmNotExistException extends GlobalException{

    private static final String MESSAGE = "유효하지 않은 id 입니다.";

    public AlarmNotExistException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
