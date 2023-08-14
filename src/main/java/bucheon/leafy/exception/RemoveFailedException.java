package bucheon.leafy.exception;

public class RemoveFailedException extends GlobalException {
        private static final String MESSAGE = "수정이 실패했습니다.";

        public RemoveFailedException() {
            super(MESSAGE);
        }
        @Override
        public int getStatusCode() {
            return 400;
        }

    }
