package com.internship.Internship.dto;

import jakarta.persistence.Id;

public class PasswordReset {
    @Id
    private String email;

    private String otp;
}
