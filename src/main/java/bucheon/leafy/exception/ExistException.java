package bucheon.leafy.exception;

public class ExistException extends GlobalException {
    private static String MESSAGE = "이미 존재하는 %s입니다.";

    public static String parseMessage(String exceptionKey){
        return String.format(MESSAGE, exceptionKey);
    }

    public ExistException(String exceptionKey) {
        super(parseMessage(exceptionKey));
    }

    @Override
    public int getStatusCode() {
        return 409;
    }
}
