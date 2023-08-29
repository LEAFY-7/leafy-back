package bucheon.leafy.exception;

public class EmailSendException extends GlobalException {

    private static final String MESSAGE = "이메일 발송에 실패하였습니다.";

    public EmailSendException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 422 ;
    }

}
