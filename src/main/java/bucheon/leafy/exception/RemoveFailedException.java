package bucheon.leafy.exception;

public class RemoveFailedException extends GlobalException {
        private static final String MESSAGE = "삭제에 실패했습니다.";

        public RemoveFailedException() {
            super(MESSAGE);
        }
        @Override
        public int getStatusCode() {
            return 500;
        }

    }
