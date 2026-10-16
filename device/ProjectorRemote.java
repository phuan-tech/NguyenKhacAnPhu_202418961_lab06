// Nguyễn Khắc An Phú 202418961
package device;

public class ProjectorRemote extends TeachingDevice {
    public ProjectorRemote(String deviceId, String deviceName, int commissioningYear, double purchasePrice, DeviceStatus status, int maxBorrowPeriods, int maxBorrowDays) {
        super(deviceId, deviceName, commissioningYear, purchasePrice, status, maxBorrowPeriods, maxBorrowDays);
    }

    public double calculateMaintenanceCost() {
        double maintenanceCost = this.getPurchasePrice() * 0.03;
        return maintenanceCost;
    }

    public String displayInfo() {
        return super.displayInfo();
    }
}
