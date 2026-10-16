// Nguyễn Khắc An Phú 202418961
package exception;

public class DeviceAlreadyReturnedException extends BorrowingException {
    public DeviceAlreadyReturnedException(String message) {
        super(message);
    }
}
