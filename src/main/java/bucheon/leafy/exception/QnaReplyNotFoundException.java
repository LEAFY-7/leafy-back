package bucheon.leafy.exception;

import bucheon.leafy.exception.GlobalException;

public class QnaReplyNotFoundException extends GlobalException {
    private static final String MESSAGE = "존재하지 않는 대댓글 입니다.";

    public QnaReplyNotFoundException() {super(MESSAGE);}

    @Override
    public int getStatusCode() {
        return 404;
    }

}
