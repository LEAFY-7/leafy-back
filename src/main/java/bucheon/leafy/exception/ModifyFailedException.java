package bucheon.leafy.exception;

public class ModifyFailedException extends GlobalException {

    private static final String MESSAGE = "수정이 실패했습니다.";

    public ModifyFailedException() {
        super(MESSAGE);
    }
<<<<<<< HEAD
=======

>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
    @Override
    public int getStatusCode() {
        return 400;
    }

}
