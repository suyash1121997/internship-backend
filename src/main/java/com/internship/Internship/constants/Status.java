package com.internship.Internship.constants;

public enum Status {
    PENDING_FOR_APPROVAL("Pending For Approval"), ACCEPTED("ACCEPTED"), REJECTED("REJECTED");
    final String status;

    Status(String status) {
        this.status = status;
    }

}
