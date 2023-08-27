package bucheon.leafy.exception;

public class NoticeNotUserIdException extends GlobalException {
    private static final String MESSAGE = "유저가 작성한 공지사항이 아닙니다.";

    public NoticeNotUserIdException() {super(MESSAGE);}

    @Override
    public int getStatusCode() {return 405;}

}
