// Nguyễn Khắc An Phú 202418961
package model;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import device.*;

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

    // Thêm thiết bị
    public void addDevice(Device device) {
        if (device == null) {
            throw new IllegalArgumentException("Thiết bị không hợp lệ");
        }

        for (Device dvc : this.devices) {
            if (dvc.getDeviceId().equals(device.getDeviceId())) {
                throw new IllegalStateException("Thiết bị đã tồn tại trong phòng, không thể thực hiện yêu cầu");
            }
        }

        this.devices.add(device);
    }

    // Xóa thiết bị
    public boolean removeDevice(String deviceId) {
        for (Device device : this.devices) {
            if (device.getDeviceId().equals(deviceId)) {
                this.devices.remove(device);
                System.out.println("Xóa thiết bị thành công");
                return true;
            }
        }
        System.out.println("Không tìm thấy thiết bị cần xóa");
        return false;
    }

    // Tìm thiết bị
    public Device findDevice(String deviceId) {
        for (Device device : this.devices) {
            if (device.getDeviceId().equals(deviceId)) {
                return device;
            }
        }
        return null;
    }

    // Tính chi phí bảo trì trong 1 năm
    public double calculateAnnualMaintenanceCost(){
        double cost = 0;
        for (Device device : devices) {
            cost += device.calculateMaintenanceCost();
        }
        return cost;
    }

    // Trả về danh sách các thiết bị cần bảo trì
    public List<Device> getDevicesRequiringMaintenance() {
        List<Device> devicesRequiringMaintenance = new ArrayList<>();
        int currentYear = LocalDateTime.now().getYear();
        for (Device device : this.devices) {
            if (device.getDeviceStatus() == DeviceStatus.UNDER_MAINTENANCE || (currentYear - device.getCommissioningYear()) > 5) {
                devicesRequiringMaintenance.add(device);
            }
        }

        return devicesRequiringMaintenance;
    }

    public List<Device> getDevices() {
        return new ArrayList<>(devices);
    }

    public String getRoomId() {
        return this.roomId;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public int getCapacity() {
        return this.capacity;
    }

    // Hiển thị thông tin
    public void displayInfo() {
        System.out.printf("Mã phòng: %s%n", this.getRoomId());
        System.out.printf("Tên phòng: %s%n", this.getRoomName());
        System.out.printf("Sức chứa: %d%n: ", this.getCapacity());
        System.out.println("Danh sách các thiết bị trong phòng: ");
        for (Device device : this.devices) {
            System.out.println(device.displayInfo());
        }
    }
}
