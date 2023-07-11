package bucheon.leafy.exception;

public class SelfTargetException extends GlobalException {

    private static final String MESSAGE = "자기 자신을 대상으로 할 수 없습니다.";

    public SelfTargetException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 500;
    }
}
