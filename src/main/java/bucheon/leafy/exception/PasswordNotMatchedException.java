package bucheon.leafy.exception;

public class PasswordNotMatchedException extends GlobalException {

    private static final String MESSAGE = "비밀번호가 일치하지않습니다.";

    public PasswordNotMatchedException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 500;
    }

}
