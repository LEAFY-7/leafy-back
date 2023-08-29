package bucheon.leafy.exception;

public class AuthenticationFailedException extends GlobalException {
    private static final String MESSAGE = "인증에 실패했습니다.";

    public AuthenticationFailedException() {super(MESSAGE);}

    @Override
    public int getStatusCode() {
        return 403;
    }

}
