// Nguyễn Khắc An Phú 202418961

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

    public BorrowItem(TeachingDevice device, LocalDate borrowedDate, LocalDate dueDate) {
        if (device == null) {
            throw new IllegalArgumentException("Thiết bị không được null");
        }

        this.device = device;
        this.borrowedDate = borrowedDate;
        this.setDueDate(dueDate);
    }

    // Ghi lại ngày trả
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
        if (fromPeriod < 1 || fromPeriod > 12) {
            throw new IllegalArgumentException("fromPeriod không hợp lệ");
        }
        this.fromPeriod = fromPeriod;
    }

    public void setToPeriod(int toPeriod) {
        if (toPeriod - this.fromPeriod > this.device.getMaxBorrowPeriods()) {
            throw new IllegalArgumentException("Không được mượn quá số tiết tối đa của thiết bị");
        }
        
        if (toPeriod < this.fromPeriod) {
            throw new IllegalArgumentException("Tiết trả không được nhỏ hơn tiết bắt đầu mượn");
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

    // Trả về thông tin 
    public String displayInfo() {
        return this.device.displayInfo()
                + "\nNgày mượn: " + this.borrowedDate
                + "\nNgày đến hạn: " + this.dueDate
                + "\nNgày trả: " + this.returnedDate;
    }
}
