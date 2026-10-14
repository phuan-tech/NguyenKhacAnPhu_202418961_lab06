package exception;

public class DuplicateDeviceException extends BorrowingException {
    public DuplicateDeviceException(String message) {
        super(message);
    }
}
