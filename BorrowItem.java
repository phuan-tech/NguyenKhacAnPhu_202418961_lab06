import java.time.LocalDate;

public class BorrowItem {
    private Device device;
    private LocalDate borrowedDate;
    private int fromPeriod;
    private int toPeriod;
    private LocalDate returnedDate = null;
    private boolean isReturned = false;

    public BorrowItem(Device device, LocalDate borrowedDate, int fromPeriod, int toPeriod) {
        if (device == null) {
            throw new IllegalArgumentException("Thiết bị không được null");
        }

        if (toPeriod < fromPeriod) {
            throw new IllegalArgumentException();
        }

        this.device = device;
        this.borrowedDate = borrowedDate;
        this.fromPeriod = fromPeriod;
        this.toPeriod = toPeriod;
    }

    public void recordReturnDate(LocalDate returnedDate) {
        if (returnedDate.isBefore(this.borrowedDate)) {
            throw new IllegalArgumentException("Ngày trả không được trước ngày mượn");
        }
        this.returnedDate = returnedDate;
        this.isReturned = true;
    }

    public Device getDevice() {
        return this.device;
    }

    public LocalDate getBorrowedDate() {
        return this.borrowedDate;
    }

    public int getFromPeriod() {
        return this.fromPeriod;
    }

    public int getToPeriod() {
        return this.toPeriod;
    }

    public void setFromPeriod(int fromPeriod) {
        this.fromPeriod = fromPeriod;
    }

    public void setToPeriod(int toPeriod) {
        this.toPeriod = toPeriod;
    }

    public LocalDate getReturnedDate() {
        return this.returnedDate;
    }

    public boolean getIsReturned() {
        return this.isReturned;
    }
}
