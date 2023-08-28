package bucheon.leafy.exception;

public class PrivateUserException extends GlobalException {

    public PrivateUserException(String userName) {
        super(userName);
    }

    @Override
    public int getStatusCode() {
        return 422;
    }
}
