package bucheon.leafy.exception;

public class NoticeNotFoundException extends GlobalException {
    private static final String MESSAGE = "존재하지 않는 공지사항입니다.";

    public NoticeNotFoundException() {super(MESSAGE);}

    @Override
    public int getStatusCode() {
        return 404;
    }

}
