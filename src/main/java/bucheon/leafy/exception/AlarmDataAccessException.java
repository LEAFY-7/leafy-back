package bucheon.leafy.exception;

public class AlarmDataAccessException extends GlobalException{

    private static final String MESSAGE = "알림 삭제에 실패했습니다.";

    public AlarmDataAccessException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 500;
    }
}
