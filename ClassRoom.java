import java.util.List;
import java.util.ArrayList;

public class ClassRoom {
    private String roomId;
    private String roomName;
    private int capacity;
    private List<Device> devices;

    public ClassRoom(String roomId, String roomName, int capacity) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.capacity = capacity;
        this.devices = new ArrayList<>();
    }

    public void addDevice(Device device) {
        if (device == null) {
            throw new IllegalArgumentException("Thiết bị không hợp lệ");
        }

        for (Device dvc : this.devices) {
            if (dvc.getDeviceId().equals(device.getDeviceId())) {
                throw new IllegalStateException("Thiết bị đã tồn tại trong phòng");
            }
        }

        this.devices.add(device);
    }

    public boolean removeDevice(String deviceId) {
        return false;
    }

    public Device findDevice(String deviceId) {
        for (Device device : this.devices) {
            if (device.getDeviceId().equals(deviceId)) {
                return device;
            }
        }
        return null;
    }

    public double calculateAnnualMaintenanceCost(){
        double cost = 0;
        for (Device device : devices) {
            cost += device.calculateMaintenanceCost();
        }
        return cost;
    }
}
