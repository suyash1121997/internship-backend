package com.internship.Internship.controller.impl;

import com.internship.Internship.controller.IForgotPasswordController;
import com.internship.Internship.service.impl.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class ForgotPasswordController implements IForgotPasswordController {
    @Autowired
    EmailService emailService;
    @Override
    public ResponseEntity<String> resetPassword(String email) throws MessagingException {
        emailService.sendSimpleMessage(email, "OTP for Password Reset", generateOTP());
        return null;
    }
    String generateOTP() {
        Random random = new Random();
        int otpValue = 100_000 + random.nextInt(900_000); // Generates a random 6-digit number
        return String.valueOf(otpValue);
    }
}
