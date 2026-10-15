package model;

public class Lecturer implements IBorrower {
    private String lecturerId;
    private String fullName;

    public Lecturer(String lecturerId, String fullName) {
        if (lecturerId == null || lecturerId.isBlank()) {
            throw new IllegalArgumentException("Mã giảng viên không được rỗng");
        }

        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Tên giảng viên không được rỗng");
        }

        this.lecturerId = lecturerId;
        this.fullName = fullName;
    }

    public String getBorrowerId() {
        return this.lecturerId;
    }

    public String getFullName() {
        return this.fullName;
    }

    public int getMaximumDevices() {
        return 3;
    }
}
