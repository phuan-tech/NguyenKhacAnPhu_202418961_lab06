package device;

public class AirConditionerRemote extends TeachingDevice {
    public AirConditionerRemote(String deviceId, String deviceName, int commissioningYear, double purchasePrice, DeviceStatus status, int maxBorrowPeriods, int maxBorrowDays) {
        super(deviceId, deviceName, commissioningYear, purchasePrice, status, maxBorrowPeriods, maxBorrowDays);
    }

    public double calculateMaintenanceCost() {
        double maintenanceCost = this.getPurchasePrice() * 0.03;
        return maintenanceCost;
    }

    public String displayInfo() {
        return super.displayInfo()
                + "Số tiết được mượn tối đa: " + this.getMaxBorrowPeriods();
    }
}
