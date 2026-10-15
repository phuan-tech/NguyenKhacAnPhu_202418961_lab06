package device;

public class Micro extends TeachingDevice {
    public Micro(String deviceId, String deviceName, int commissioningYear, double purchasePrice, DeviceStatus status, int maxBorrowPeriods, int maxBorrowDays) {
        super(deviceId, deviceName, commissioningYear, purchasePrice, status, maxBorrowPeriods, maxBorrowDays);
    }

    public double calculateMaintenanceCost() {
        double maintenanceCost = this.getPurchasePrice() * 0.08;
        return maintenanceCost;
    }

    public String displayInfo() {
        return super.displayInfo();
    }
}
