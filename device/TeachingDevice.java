// Nguyễn Khắc An Phú 202418961
package device;

public abstract class TeachingDevice extends Device {
    private int maxBorrowPeriods;
    private int maxBorrowDays;

    public TeachingDevice(String deviceId, String deviceName, int commissioningYear, double purchasePrice, DeviceStatus status, int maxBorrowPeriods, int maxBorrowDays) {
        super(deviceId, deviceName, commissioningYear, purchasePrice, status);
        if (maxBorrowPeriods <= 0) {
            throw new IllegalArgumentException("Số tiết mượn tối đa phải lớn hơn 0");
        }

        if (maxBorrowDays < 0) {
            throw new IllegalArgumentException("Số ngày mượn tối đa không được âm");
        }
        this.maxBorrowPeriods = maxBorrowPeriods;
        this.maxBorrowDays = maxBorrowDays;
    }

    public int getMaxBorrowPeriods() {
        return this.maxBorrowPeriods;
    }

    public int getMaxBorrowDays() {
        return this.maxBorrowDays;
    }

    public String displayInfo() {
        return super.displayInfo()
                + "\nSố tiết được mượn tối đa: " + this.maxBorrowPeriods
                + "\nSố ngày được mượn tối đa: " + this.maxBorrowDays;
    }
}
