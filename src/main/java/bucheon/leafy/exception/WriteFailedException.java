package bucheon.leafy.exception;

public class WriteFailedException extends GlobalException {
    private static final String MESSAGE = "등록에 실패했습니다.";

    public WriteFailedException() {super(MESSAGE);}

    @Override
    public int getStatusCode() {
        return 500;
    }

}
