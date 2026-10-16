// Nguyễn Khắc An Phú 202418961
package model;

public class TeachingAssistant implements IBorrower {
    private String teachingAssistantId;
    private String fullName;

    public TeachingAssistant(String teachingAssistantId, String fullName) {
        if (teachingAssistantId == null || teachingAssistantId.isBlank()) {
            throw new IllegalArgumentException("Mã trợ giảng không được rỗng");
        }

        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Tên trợ giảng không được rỗng");
        }

        this.teachingAssistantId = teachingAssistantId;
        this.fullName = fullName;
    }

    public String getBorrowerId() {
        return this.teachingAssistantId;
    }

    public String getFullName() {
        return this.fullName;
    }

    public int getMaximumDevices() {
        return 2;
    }
}
