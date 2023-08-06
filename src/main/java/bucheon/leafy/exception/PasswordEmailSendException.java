package bucheon.leafy.exception;

public class PasswordEmailSendException extends GlobalException {

    private static final String MESSAGE = "임시 비밀번호 이메일 발송에 실패하였습니다.";

    public PasswordEmailSendException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }

}
