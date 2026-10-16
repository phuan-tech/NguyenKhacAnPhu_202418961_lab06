// Nguyễn Khắc An Phú 202418961

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import device.*;
import exception.*;
import model.*;

public class Main {
    public static void main(String[] args) throws BorrowingException {
        System.out.println("-------Luồng thành công-------");
        System.out.println("1. Tạo một giảng viên");

        Lecturer lecturer1 = new Lecturer("GV001", "Nguyễn Văn A");
        System.out.println("Tạo giảng viên thành công");

        System.out.println("2. Tạo một điều khiển máy chiếu, một điều khiển điều hòa và một micro không dây");

        ProjectorRemote projectorRemote1 = new ProjectorRemote("PR001", 
                                                                "Điều khiển máy chiếu", 
                                                                2024, 
                                                                500000, 
                                                                DeviceStatus.AVAILABLE, 
                                                                2, 
                                                                3);
        System.out.println("Tạo điều khiển máy chiếu thành công");

        AirConditionerRemote airConditionerRemote1 = new AirConditionerRemote("AR001", 
                                                                            "Điều khiển điều hòa", 
                                                                            2025, 
                                                                            300000, 
                                                                            DeviceStatus.AVAILABLE, 
                                                                            2, 
                                                                            2);
        System.out.println("Tạo điều khiển điều hòa thành công");

        Micro microphone1 = new Micro("MC001", 
                                    "Micro không dây", 
                                    2023, 
                                    1500000, 
                                    DeviceStatus.AVAILABLE, 
                                    1, 
                                    1);
        System.out.println("Tạo micro không dây thành công");

        System.out.println("3. Tạo phiếu mượn cho một phòng dạy");
        BorrowingSlip slip1 = new BorrowingSlip("PM001", lecturer1, "P001", LocalDate.now());
        System.out.println("Tạo phiếu mượn thành công");

        System.out.println("4. Thêm điều khiển máy chiếu và micro vào phiếu");
        slip1.addDevice(projectorRemote1, 2);
        System.out.println("Thêm điều khiển máy chiếu thành công");
        slip1.addDevice(microphone1, 1);
        System.out.println("Thêm micro vào phiếu thành công");

        System.out.println("5. Trả điều khiển máy chiếu đúng hạn");
        double lateFee1 = slip1.returnDevice("PR001", LocalDate.now());
        System.out.println("Trả máy chiếu thành công");
        System.out.printf("Phí trả muộn: %.2f VNĐ%n", lateFee1);

        System.out.println("6. Trả micro muộn 2 ngày");
        double lateFee2 = slip1.returnDevice("MC001", LocalDate.now().plusDays(3));
        System.out.println("Trả micro thành công");
        System.out.printf("Phí trả muộn: %.2f VNĐ%n", lateFee2);

        System.out.println("7. Hiển thị các phiếu trả muộn, chưa trả");
        // Tạo thiết bị mới
        Micro microphone2 = new Micro(
                "MC002",
                "Micro không dây 2",
                2024,
                1500000,
                DeviceStatus.AVAILABLE,
                1,
                10
        );

        Micro microphone3 = new Micro(
                "MC003",
                "Micro không dây 3",
                2024,
                1500000,
                DeviceStatus.AVAILABLE,
                1,
                10
        );

        // Trường hợp 1: Phiếu mượn đã quá hạn nhưng chưa trả
        BorrowingSlip slip2 = new BorrowingSlip(
                "PM002",
                lecturer1,
                "P002",
                LocalDate.now().minusDays(5)
        );

        slip2.addDevice(microphone2, 2);

        System.out.println("Tạo phiếu PM002: thiết bị chưa trả, đã quá hạn");

        // Trường hợp 2: Phiếu mượn chưa đến hạn và chưa trả
        BorrowingSlip slip3 = new BorrowingSlip(
                "PM003",
                lecturer1,
                "P003",
                LocalDate.now()
        );

        slip3.addDevice(microphone3, 3);

        System.out.println("Tạo phiếu PM003: thiết bị chưa trả, chưa quá hạn");

        List<BorrowingSlip> slips = new ArrayList<>();

        slips.add(slip1);
        slips.add(slip2);
        slips.add(slip3);

        LocalDate today = LocalDate.now();
        System.out.println("Hiển thị các phiếu trả muộn, chưa trả: ");

        for (BorrowingSlip slip : slips) {
            boolean needsDisplay = false;

            for (BorrowItem item : slip.getItems()) {
                // Thiết bị chưa trả và đã quá hạn
                if (!item.getIsReturned()
                        && today.isAfter(item.getDueDate())) {
                    needsDisplay = true;
                    break;
                }

                // Thiết bị đã trả nhưng trả muộn
                if (item.getIsReturned()
                        && item.getReturnedDate().isAfter(item.getDueDate())) {
                    needsDisplay = true;
                    break;
                }
            }

            if (needsDisplay) {
                slip.displayInfo();
                System.out.println("Phí trả muộn: "
                        + slip.calculateTotalLateFee() + " VNĐ");
                System.out.println("------------------------------");
            }
        }

        System.out.println("-------Luồng phát sinh ngoại lệ-------");
        System.out.println("1. Mượn điều khiển điều hòa đang UnderMaintenance");
        AirConditionerRemote airConditionerRemote2 = new AirConditionerRemote("AR002", 
                                                                            "Điều khiển điều hòa 2", 
                                                                            2025, 
                                                                            300000, 
                                                                            DeviceStatus.UNDER_MAINTENANCE, 
                                                                            2, 
                                                                            2);
        try {
            slip1.addDevice(airConditionerRemote2, 2);
            System.out.println("Mượn thiết bị thành công");
        } catch (DeviceUnavailableException e) {
            System.out.println("Lỗi! " + e.getMessage());
        }
        
        System.out.println("2. Trợ giảng mượn thiết bị thứ 3");
        TeachingAssistant teachingAssistant1 = new TeachingAssistant("TA001", "Trần Thị B");
        BorrowingSlip slip4 = new BorrowingSlip("PM004", teachingAssistant1, "P004", today);
        slip4.addDevice(projectorRemote1, 1);
        slip4.addDevice(airConditionerRemote1, 1);
        try {
            slip4.addDevice(microphone1, 1);
            System.out.println("Thêm thiết bị thành công");
        } catch (BorrowLimitExceededException e) {
            System.out.println("Lỗi! " + e.getMessage());
        }

        System.out.println("3. Thêm cùng một micro hai lần");
        try {
            slip1.addDevice(microphone1, 1);
            System.out.println("Thêm thiết bị thành công");
        } catch (DuplicateDeviceException e) {
            System.out.println("Lỗi! " + e.getMessage());
        }

        System.out.println("4. Mượn micro trong 3 ngày dù giới hạn là 1 ngày");
        try {
            slip4.addDevice(microphone1, 3);
            System.out.println("Mượn thiết bị thành công");
        } catch (InvalidBorrowPeriodException e) {
            System.out.println("Lỗi! " + e.getMessage());
        }

        System.out.println("5. Trả một điều khiển máy chiếu không thuộc phiếu");
        ProjectorRemote projectorRemote2 = new ProjectorRemote("PR002", 
                                                                "Điều khiển máy chiếu 2", 
                                                                2024, 
                                                                500000, 
                                                                DeviceStatus.AVAILABLE, 
                                                                2, 
                                                                3);
        try {
            slip1.returnDevice("PR002", today);
            System.out.println("Trả điều khiển máy chiếu thành công");
        } catch (DeviceNotInSlipException e) {
            System.out.println("Lỗi! " + e.getMessage());
        }

        System.out.println("6. Trả cùng một thiết bị 2 lần");
        try {
            slip1.returnDevice("PR001", today);
            System.out.println("Trả thiết bị thành công");
        } catch (DeviceAlreadyReturnedException e) {
            System.out.println("Lỗi! " + e.getMessage());
        }
    }
}