package exception;

public class InvalidBorrowPeriodException extends BorrowingException {
    private String deviceId;
    private int borrowDays;
    private int maxBorrowDays;

    public InvalidBorrowPeriodException(String deviceId, int borrowDays, int maxBorrowDays) {
        super("Số ngày mượn không dương");
        this.deviceId = deviceId;
        this.borrowDays = borrowDays;
        this.maxBorrowDays = maxBorrowDays;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public int borrowDays() {
        return this.borrowDays;
    }

    public int maxBorrowDays() {
        return this.maxBorrowDays;
    }
}
