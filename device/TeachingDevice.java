package device;

public abstract class TeachingDevice extends Device {
    private int maxBorrowPeriods;
    private int maxBorrowDays;

    public TeachingDevice(String deviceId, String deviceName, int commissioningYear, double purchasePrice, DeviceStatus status, int maxBorrowPeriods, int maxBorrowDays) {
        super(deviceId, deviceName, commissioningYear, purchasePrice, status);
        if (maxBorrowPeriods < 0) {
            throw new IllegalArgumentException();
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
                + "Số tiết được mượn tối đa: " + this.maxBorrowPeriods
                + "Số ngày được mượn tối đa: " + this.maxBorrowDays;
    }
}
