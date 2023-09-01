package bucheon.leafy.exception;

public class OAuth2InfoNotExistException extends GlobalException {

    private static final String MESSAGE = "간편 로그인 정보가 올바르지 않습니다.";

    public OAuth2InfoNotExistException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 500;
    }
}
