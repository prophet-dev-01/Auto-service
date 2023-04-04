package project.autoservice.model;

public enum PaymentStatus {
    PAID("paid"),
    UNPAID("unpaid");

    public String status;

    PaymentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
