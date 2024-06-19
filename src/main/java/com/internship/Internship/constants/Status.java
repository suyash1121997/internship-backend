package com.internship.Internship.constants;

import com.internship.Internship.exception.InternshipException;

public enum Status {
    PENDING_FOR_APPROVAL("Pending For Approval"), ACCEPTED("ACCEPTED"), REJECTED("REJECTED");
    final String status;

    Status(String status) {
        this.status = status;
    }

    public static String getValue(String status) {
        if(status == null) {
            throw new InternshipException("Status field is null");
        }
        return Status.valueOf(status.toUpperCase()).name();
    }
}
