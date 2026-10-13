public class DeviceUnavailableException extends BorrowingException {
    private String deviceId;
    private DeviceStatus currentStatus;

    public DeviceUnavailableException(String deviceId, DeviceStatus currentStatus) {
        super("Thiết bị" + deviceId + "không ở trạng thái AVAILABLE");
        this.deviceId = deviceId;
        this.currentStatus = currentStatus;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public DeviceStatus getCurrentStatus() {
        return this.currentStatus;
    }
}
