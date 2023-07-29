package bucheon.leafy.exception;

public class UserPasswordDataAccessException extends GlobalException{

    private static final String MESSAGE = "비밀번호 수정에 실패했습니다.";

    public UserPasswordDataAccessException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 500;
    }
}
