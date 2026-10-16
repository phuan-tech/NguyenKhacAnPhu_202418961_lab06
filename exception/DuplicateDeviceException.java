// Nguyễn Khắc An Phú 202418961

package exception;

public class DuplicateDeviceException extends BorrowingException {
    public DuplicateDeviceException(String message) {
        super(message);
    }
}
