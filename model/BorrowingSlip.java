// Nguyễn Khắc An Phú 202418961

package model;

import java.time.LocalDate;
import java.util.List;

import device.DeviceStatus;
import device.TeachingDevice;
import java.util.ArrayList;
import java.util.Collections;
import java.time.temporal.ChronoUnit;
import exception.*;

public class BorrowingSlip {
    private String slipId;
    private IBorrower borrower;
    private String teachingRoom;
    private LocalDate borrowedDate;
    private final List<BorrowItem> items = new ArrayList<>();

    public BorrowingSlip(String slipId, IBorrower borrower, String teachingRoom, LocalDate borrowedDate) {
        if (slipId == null || slipId.isBlank()) {
            throw new IllegalArgumentException("Mã phiếu mượn không được rỗng");
        }

        if (borrower == null) {
            throw new IllegalArgumentException("Người mượn không hợp lệ");
        }

        if (teachingRoom == null || teachingRoom.isBlank()) {
            throw new IllegalArgumentException("Tên phòng dạy không được rỗng");
        }

        if (borrowedDate == null) {
            throw new IllegalArgumentException("Ngày mượn không hợp lệ");
        }

        this.slipId = slipId;
        this.borrower = borrower;
        this.teachingRoom = teachingRoom;
        this.borrowedDate = borrowedDate;
    }

    // Thêm thiết bị
    public void addDevice(TeachingDevice device, int borrowDays) throws BorrowingException {
        if (device == null) {
            throw new IllegalArgumentException("Thiết bị không được null");
        }

        // Kiểm tra số ngày mượn
        if (borrowDays <= 0 || borrowDays > device.getMaxBorrowDays()) {
            throw new InvalidBorrowPeriodException(device.getDeviceId(), borrowDays, device.getMaxBorrowDays());
        }

        // Kiểm tra thiết bị đã có trong phiếu hay chưa
        for (BorrowItem item : this.items) {
            if (item.getTeachingDevice().getDeviceId().equals(device.getDeviceId())) {
                throw new DuplicateDeviceException("Thiết bị đã tồn tại trong phiếu");
            }
        }

        // Kiểm tra giới hạn mượn thiết bị
        if (this.items.size() >= this.borrower.getMaximumDevices()) {
            throw new BorrowLimitExceededException(this.borrower.getBorrowerId(), this.borrower.getMaximumDevices());
        }

        // Kiểm tra trạng thái thiết bị
        if (device.getDeviceStatus() != DeviceStatus.AVAILABLE) {
            throw new DeviceUnavailableException(device.getDeviceId(), device.getDeviceStatus());
        }

        LocalDate borrowedDate = this.borrowedDate;
        LocalDate dueDate = borrowedDate.plusDays(borrowDays);

        BorrowItem newBorrowItem = new BorrowItem(device, borrowedDate, dueDate);
        device.markAsBorrowed();

        items.add(newBorrowItem);
    }

    // Trả thiết bị và tính phí trả muộn
    public double returnDevice(String deviceId, LocalDate returnedDate) throws BorrowingException {
        if (deviceId == null || deviceId.isBlank()) {
            throw new IllegalArgumentException("Mã thiết bị không được rỗng");
        }

        if (returnedDate == null) {
            throw new IllegalArgumentException("Ngày trả không hợp lệ");
        }

        for (BorrowItem item : this.items) {
            if (item.getTeachingDevice().getDeviceId().equals(deviceId)) {
                if (item.getIsReturned()) {
                    throw new DeviceAlreadyReturnedException("Thiết bị trong phiếu đã được trả trước đó");
                }
                item.recordReturnDate(returnedDate);
                item.getTeachingDevice().markAsAvailable();
                long lateDays = Math.max(0, ChronoUnit.DAYS.between(item.getDueDate(), returnedDate));
                double lateFee = 20_000 * lateDays;
                return lateFee;
            }
        }

        throw new DeviceNotInSlipException("Thiết bị không thuộc phiếu");
    }

    // Tính tổng phí trả muộn
    public double calculateTotalLateFee() {
        double totalLateFee = 0;
        for (BorrowItem item : this.items) {
            if (item.getIsReturned()) {
                long lateDays = Math.max(0, ChronoUnit.DAYS.between(item.getDueDate(), item.getReturnedDate()));
                totalLateFee += 20_000 * lateDays;
            }
        }
        return totalLateFee;
    }

    public List<BorrowItem> getItems() {
        return Collections.unmodifiableList(this.items);
    }

    public String getSlipId() {
        return this.slipId;
    }

    public IBorrower getBorrower() {
        return this.borrower;
    }

    public String getTeachingRoom() {
        return this.teachingRoom;
    }

    public LocalDate getBorrowedDate() {
        return this.borrowedDate;
    }

    // Hiển thị thông tin
    public void displayInfo() {
        System.out.printf("Mã phiếu mượn: %s%n", this.slipId);
        System.out.println("Thông tin người mượn: ");
        System.out.printf("Mã người mượn: %s%n", this.borrower.getBorrowerId());
        System.out.printf("Tên người mượn: %s%n", this.borrower.getFullName());
        System.out.printf("Phòng dạy: %s%n", this.teachingRoom);
        System.out.println("Ngày mượn" + this.borrowedDate);
        System.out.println("Các thiết bị đã mượn: ");
        for (BorrowItem item : this.items) {
            System.out.println(item.displayInfo());
        }
    }
}
