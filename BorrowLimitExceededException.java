public class BorrowLimitExceededException extends BorrowingException {
    private String borrowerId;
    private int maximumDevices;

    public BorrowLimitExceededException(String borrowerId, int maximumDevices) {
        super("Người mượn" + borrowerId + "đã đạt giới hạn thiết bị");
        this.borrowerId = borrowerId;
        this.maximumDevices = maximumDevices;
    }

    public String getBorrowerId() {
        return this.borrowerId;
    }

    public int getMaximumDevices() {
        return this.maximumDevices;
    }
}
