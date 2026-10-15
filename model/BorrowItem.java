package model;

import java.time.LocalDate;
import device.Device;
import device.TeachingDevice;

public class BorrowItem {
    private TeachingDevice device;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private int fromPeriod;
    private int toPeriod;
    private LocalDate returnedDate = null;
    private boolean isReturned = false;

    public BorrowItem(TeachingDevice device, LocalDate borrowedDate, LocalDate dueDate, int fromPeriod, int toPeriod) {
        if (device == null) {
            throw new IllegalArgumentException("Thiết bị không được null");
        }

        this.device = device;
        this.borrowedDate = borrowedDate;
        this.setDueDate(dueDate);
        this.setFromPeriod(fromPeriod);
        this.setToPeriod(toPeriod);
    }

    public void recordReturnDate(LocalDate returnedDate) {
        if (returnedDate.isBefore(this.borrowedDate)) {
            throw new IllegalArgumentException("Ngày trả không được trước ngày mượn");
        }
        this.returnedDate = returnedDate;
        this.isReturned = true;
    }

    public Device getTeachingDevice() {
        return this.device;
    }

    public LocalDate getBorrowedDate() {
        return this.borrowedDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
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
        if (toPeriod < this.fromPeriod) {
            throw new IllegalArgumentException();
        }

        this.toPeriod = toPeriod;
    }

    public LocalDate getReturnedDate() {
        return this.returnedDate;
    }

    public boolean getIsReturned() {
        return this.isReturned;
    }

    public void setDueDate(LocalDate dueDate) {
        if (dueDate.isBefore(this.borrowedDate)) {
            throw new IllegalArgumentException();
        }

        this.dueDate = dueDate;
    }

    public boolean isOverdue(LocalDate currentDate) {
        if (this.isReturned) {
            return false;
        }

        return currentDate.isAfter(this.dueDate);
    }

    public boolean wasReturnedLate() {
        return this.isReturned && this.returnedDate.isAfter(this.dueDate);
    }

    public String displayInfo() {
        return this.device.displayInfo()
                + "Ngày mượn: " + this.borrowedDate
                + "Ngày đến hạn: " + this.dueDate
                + "Ngày trả: " + this.returnedDate;
    }
}
