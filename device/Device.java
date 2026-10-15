package device;
import java.time.LocalDate;

public abstract class Device {
    private String deviceId;
    private String deviceName;
    private int commissioningYear;
    private double purchasePrice;
    private DeviceStatus status;

    public Device(String deviceId, String deviceName, int commissioningYear, double purchasePrice, DeviceStatus status) {
        if (deviceId == null || deviceId.isBlank()) {
            throw new IllegalArgumentException("Mã thiết bị không được rỗng");
        }

        if (deviceName == null || deviceName.isBlank()) {
            throw new IllegalArgumentException("Tên thiết bị không được rỗng");
        }

        int currentYear = LocalDate.now().getYear();
        if (commissioningYear > currentYear) {
            throw new IllegalArgumentException("Năm đưa vào sử dụng không được lớn hơn năm hiện tại");
        }

        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.commissioningYear = commissioningYear;
        this.purchasePrice = purchasePrice;
        this.status = status;
    }

    public abstract double calculateMaintenanceCost();

    public String displayInfo() {
        return "Mã thiết bị: " + this.deviceId
                + "Tên thiết bị: " + this.deviceName
                + "Năm đưa vào sử dụng: " + this.commissioningYear
                + "Giá mua: " + this.purchasePrice
                + "Trạng thái hoạt động: " + this.status;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public int getCommissioningYear() {
        return this.commissioningYear;
    }

    public double getPurchasePrice() {
        return this.purchasePrice;
    }

    public DeviceStatus getDeviceStatus() {
        return this.status;
    }

    public void markAsBorrowed() {
        this.status = DeviceStatus.BORROWED;
    }

    public void markAsAvailable() {
        this.status = DeviceStatus.AVAILABLE;
    }
}
