package exception;

public class DeviceAlreadyReturnedException extends BorrowingException {
    public DeviceAlreadyReturnedException(String message) {
        super(message);
    }
}
