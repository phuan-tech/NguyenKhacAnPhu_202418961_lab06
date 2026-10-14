import java.time.LocalDate;
import java.util.List;
import device.TeachingDevice;
import java.util.ArrayList;

public class BorrowingSlip {
    private String slipId;
    private IBorrower borrower;
    private String teachingRoom;
    private LocalDate borrowedDate;
    private List<BorrowItem> items;

    public BorrowingSlip(String slipId, IBorrower borrower, String teachingRoom, LocalDate borrowedDate) {
        this.slipId = slipId;
        this.borrower = borrower;
        this.teachingRoom = teachingRoom;
        this.borrowedDate = borrowedDate;
        this.items = new ArrayList<>();
    }

    public void addDevice(TeachingDevice device, int borrowDays) {
        LocalDate borrowedDate = LocalDate.now();
        LocalDate dueDate = LocalDate.of(borrowedDate.getYear(), borrowedDate.getMonth(), borrowedDate.getDayOfMonth() + borrowDays);

        BorrowItem newBorrowItem = new BorrowItem(device, borrowedDate, dueDate, 0, 12);

        items.add(newBorrowItem);
    }
}
