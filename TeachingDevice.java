public abstract class TeachingDevice extends Device {
    private int maxBorrowPeriods;

    public TeachingDevice(String deviceId, String deviceName, int commissioningYear, double purchasePrice, DeviceStatus status, int maxBorrowPeriods) {
        super(deviceId, deviceName, commissioningYear, purchasePrice, status);
        if (maxBorrowPeriods < 0) {
            throw new IllegalArgumentException();
        }
        this.maxBorrowPeriods = maxBorrowPeriods;
    }
}
