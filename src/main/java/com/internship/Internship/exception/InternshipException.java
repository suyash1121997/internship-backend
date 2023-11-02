package com.internship.Internship.exception;

import com.internship.Internship.dto.ResponseModel;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class InternshipException extends Exception {
    public InternshipException(String message) {
        super(message);

    }

    @Getter
    private int errorCode;
    @Getter
    private String message;
    @Getter
    private boolean isUserExists;

    public InternshipException(int errorCode, String message, boolean isUserExist) {
        this.errorCode = errorCode;
        this.message = message;
        this.isUserExists = isUserExist;
    }

    public InternshipException(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

}
