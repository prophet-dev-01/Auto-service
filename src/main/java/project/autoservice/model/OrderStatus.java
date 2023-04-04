package project.autoservice.model;

public enum OrderStatus {
    ACCEPTED("accepted"),
    IN_PROGRESS("in progress"),
    SUCCESSFUL("successful"),
    NOT_COMPLETED_SUCCESSFUL("not completed successful"),
    PAID("paid");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
